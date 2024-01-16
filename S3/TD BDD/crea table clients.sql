INSERT INTO Utilisateur(emailutilisateur) VALUES('cli1');
INSERT INTO Utilisateur(emailutilisateur) VALUES('cli2');
INSERT INTO Utilisateur(emailutilisateur) VALUES('cli3');

INSERT INTO Client VALUES('cli1','cli1','cli1','cli1','acli1','centre','124',200);
INSERT INTO Client VALUES('cli2','cli2','cli2','cli1','acli1','centre','124',200);
INSERT INTO Client VALUES('cli3','cli3','cli3','cli1','acli1','centre','125',200);

INSERT INTO Album VALUES(1,'t1','01/JAN/2015','01/JAN/2015','Public','cli1');
INSERT INTO Album VALUES(2,'t2','01/JAN/2015','01/JAN/2015','Public','cli2');
INSERT INTO Album VALUES(3,'t3','01/JAN/2015','01/JAN/2015','Public','cli1');

INSERT INTO image VALUES(1,1,'t11',3,'a','23/DEC/2015');
INSERT INTO image VALUES(1,2,'t12',3,'a','23/DEC/2015');
INSERT INTO image VALUES(2,3,'t21',3,'a','23/DEC/2015');
INSERT INTO image VALUES(2,4,'t22',3,'a','23/DEC/2015');
INSERT INTO image VALUES(2,5,'t23',3,'a','23/DEC/2015');
INSERT INTO image VALUES(3,6,'t13',3,'a','23/DEC/2015');

INSERT INTO Laboratoire VALUES('idLabo1','rs1','adrelabo1','labo1','1234');

INSERT INTO Commande(numCommande,dateCommande,adresseLivraison,etatCommande,eMailClient,idLaboratoire) VALUES('com1','01/JAN/2015','adr1','Attente Paiement','cli1','idLabo1');
INSERT INTO Commande(numCommande,dateCommande,adresseLivraison,etatCommande,eMailClient,idLaboratoire) VALUES('com2','01/JAN/2015','adr1','Livrée','cli1','idLabo1');
INSERT INTO Commande(numCommande,dateCommande,adresseLivraison,etatCommande,eMailClient,idLaboratoire) VALUES('com3','01/JAN/2015','adr1','Livrée','cli2','idLabo1');
INSERT INTO Commande(numCommande,dateCommande,adresseLivraison,etatCommande,eMailClient,idLaboratoire) VALUES('com4','01/JAN/2015','adr1','Attente Paiement','cli3','idLabo1');


/*   1   */


CREATE OR REPLACE VIEW AlbumsClient( eMailClient,nbAlbum ) AS
SELECT eMailClient, COUNT(*)
FROM Album
GROUP BY eMailClient
ORDER BY eMailClient;


CREATE OR REPLACE VIEW AlbumsToutClient( eMailClient,nbAlbum ) AS
SELECT eMailUtilisateur, NVL(nbAlbum,0)
FROM Client LEFT JOIN AlbumsClient
ON eMailUtilisateur = eMailClient
ORDER BY eMailUtilisateur;

SELECT * FROM AlbumsToutClient;





CREATE OR REPLACE VIEW ImagesClient( eMailClient,nbImage ) AS
SELECT eMailClient, COUNT(*)
FROM Album NATURAL JOIN Image
GROUP BY eMailClient
ORDER BY eMailClient;


CREATE OR REPLACE VIEW ImagesToutClient( eMailClient,nbImage ) AS
SELECT eMailUtilisateur, NVL(nbImage,0)
FROM Client LEFT JOIN ImagesClient
ON eMailUtilisateur = eMailClient
ORDER BY eMailUtilisateur;

SELECT * FROM ImagesToutClient;





CREATE OR REPLACE VIEW Commande2015NL( eMailClient,nbCom ) AS
SELECT eMailClient, COUNT(*)
FROM Commande
WHERE to_char(dateCommande,'YYYY') = '2015'
AND etatCommande != 'Livrée'                                    /* ou <> | la même chose que != */
GROUP BY eMailClient;


CREATE OR REPLACE VIEW Commande2015NLToutClient( eMailClient,nbCom ) AS
SELECT eMailUtilisateur, NVL(nbCom,0)
FROM Client LEFT JOIN Commande2015NL
ON eMailUtilisateur = eMailClient
ORDER BY eMailUtilisateur;

SELECT * FROM Commande2015NLToutClient;





SELECT eMailUtilisateur, nomClient, region, nbAlbum, nbImage, nbCom
FROM Client
JOIN (
    AlbumsToutClient NATURAL JOIN ImagesToutClient
    NATURAL JOIN Commande2015NLToutClient
) ON emailutilisateur = eMailClient
ORDER BY eMailUtilisateur;



/*   2   */



CREATE OR REPLACE VIEW Commande2015NonOk(numCommande,idLaboratoire,eMailClient) AS
SELECT numCommande, idLaboratoire, eMailClient
FROM Commande
WHERE etatCommande != 'Livrée' AND to_char(dateCommande,'YYYY') = '2015';

CREATE OR REPLACE VIEW q11_B_1(numCommande,emailutilisateur,nomClient,region) AS
SELECT numCommande, emailutilisateur, nomClient, region
FROM client, Commande2015NonOk
WHERE emailutilisateur = eMailClient;

CREATE OR REPLACE VIEW q11_B_2(numCommande,dateCommande,etatCommande) AS
SELECT c1.numCommande, dateCommande,etatCommande
FROM Commande2015NonOk c1, Commande c2
WHERE c1.numCommande = c2.numCommande;

CREATE OR REPLACE VIEW q11_B_3(numCommande,raisonSociale,eMailLabo) AS
SELECT numCommande,raisonSociale,eMailLabo
FROM Commande2015NonOk c, Laboratoire labo
WHERE c.idLaboratoire=labo.idLaboratoire;

CREATE OR REPLACE VIEW nbTirageParCommande(numCommande,idLaboratoire,nbTirage) AS
SELECT co.numCommande, co.idLaboratoire, quantite
FROM Commande2015NonOk co, TirageImages ti
WHERE co.numCommande=ti.numCommande;

CREATE OR REPLACE VIEW ristourneParCommande(numCommande,reduction) AS
SELECT numCommande, NVL(reduction,0)
FROM nbTirageParCommande nbT, TarifDegressif TD
WHERE nbT.idLaboratoire=TD.idLaboratoire AND nbT.nbTirage<quantiteMaxi AND quantiteMini<nbT.nbTirage;

CREATE OR REPLACE VIEW montantSansReduction(numCommande,montant) AS
SELECT nbT.numCommande, (nbT.nbTirage * tf.prixUnitaire)
FROM nbTirageParCommande nbT, TarifFormat tf
WHERE nbT.idLaboratoire = tf.idLaboratoire;

CREATE OR REPLACE VIEW q11_B_4(numCommande,APayer) AS
SELECT mSR.numCommande, (mSr.montant * (1-rPC.reduction))
FROM montantSansReduction mSR, ristourneParCommande rPC
WHERE mSR.numCommande = rPC.numCommande;

SELECT v1.numCommande, v1.nomClient, v1.region, v2.dateCommande, v2.etatCommande, v3.raisonSociale, v3.eMailLabo, v4.APayer
FROM q11_B_1 v1, q11_B_2 v2, q11_B_3 v3, q11_B_4
WHERE v1.numCommande=v4.numCommande;