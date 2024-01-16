SELECT * FROM
(
    SELECT eMailUtilisateur, nomClient, telClient FROM
    (
        SELECT eMailUtilisateur, nomClient, telClient
        FROM(
            SELECT eMailUtilisateur, nomClient, telClient, idAlbum
            FROM Client
            JOIN(
                SELECT *
                From Album
                where visibilite='public') a
            ON ( a.eMailClient=eMailUtilisateur AND region='Centre')
            ) vue1
        join(SELECT *
            FROM(
                SELECT Image.idAlbum
                FROM Image
                JOIN(SELECT *
                FROM Notation
                where note=18) no
                ON (Image.idAlbum=no.idAlbum AND Image.dateTelechargement<'15/03/2017')
                ) V1
            ) vue2
        ON (vue1.idAlbum=vue2.idAlbum)
    )
);


/*

*/

DROP VIEW client1;
DROP VIEW album1;
DROP VIEW vue1;
DROP VIEW image1;
DROP VIEW notation1;
DROP VIEW vue2;


CREATE VIEW client1(eMailUtilisateur, nomClient, telClient) AS
SELECT eMailUtilisateur, nomClient, telClient FROM Client WHERE region='Centre';

CREATE VIEW album1(idAlbum, eMailClient) AS
SELECT idAlbum, eMailClient FROM Album WHERE visibilite='public';

CREATE VIEW vue1(eMailUtilisateur, nomClient, telClient, idAlbum) AS
SELECT eMailUtilisateur, nomClient, telClient, idAlbum
FROM client1
JOIN album1
ON client1.eMailUtilisateur=album1.eMailClient;

CREATE VIEW image1(idAlbum, numImage) AS
SELECT idAlbum, numImage FROM Image WHERE dateTelechargement<'20171503';

CREATE VIEW notation1(idAlbum, numImage) AS
SELECT idAlbum, numImage FROM Notation WHERE note=18;

CREATE VIEW vue2(idAlbum) AS
SELECT idAlbum
FROM image1
JOIN notation1
ON image1.idAlbum=notation1.idAlbum;

SELECT eMailUtilisateur, nomClient, telClient
FROM vue1
JOIN vue2
on vue1.idAlbum=vue2.idAlbum;

/*

*/


CREATE VIEW v1(tarifs, idLaboratoire, quantiteMini, quantiteMaxi, reduction) AS
SELECT COUNT(idLaboratoire), idLaboratoire, quantiteMini, quantiteMaxi, reduction
FROM TarifDegressif;

CREATE VIEW v2(idLaboratoire, quantiteMini, quantiteMaxi, reduction) AS
SELECT idLaboratoire, quantiteMini, quantiteMaxi, reduction
FROM v1
WHERE tarifs=1;

SELECT eMailLabo, raisonSociale, quantiteMini, quantiteMaxi, reduction
FROM laboratoire
NATURAL JOIN v2;




SELECT eMailLabo, raisonSociale, quantiteMini, quantiteMaxi, reduction
FROM TarifDegressif NATURAL JOIN Laboratoire
    NATURAL JOIN
    (
        SELECT idLaboratoire
        FROM TarifDegressif
        MINUS
        SELECT t1.idLaboratoire
        FROM TarifDegressif t1 JOIN TarifDegressif t2
        ON (t1.idLaboratoire = t2.idLaboratoire
            AND (t1.quantiteMini != t2.quantiteMini
                OR t1.quantiteMaxi != t2.quantiteMaxi
            )
        )
    )


SELECT eMailLabo, raisonSociale, quantiteMini, quantiteMaxi, reduction
FROM TarifDegressif NATURAL JOIN Laboratoire
WHERE idLaboratoire IN
    (
        SELECT idLaboratoire
        FROM TarifDegressif
        GROUP BY idLaboratoire
        HAVING COUNT(*) = 1
    );


/*


*/



SELECT eMailUtilisateur, nomClient, telClient
FROM Client
    JOIN
    (( SELECT *
    FROM Album
    WHERE visibilite='Privé') a1)



/* eMailClient de ceux qui ont 2 Albums prive */
SELECT eMailClient
FROM (
    SELECT eMailClient, idAlbum, visibilite
    FROM Album
    WHERE visibilite='prive'
)
GROUP BY idAlbum
HAVING COUNT(*) = 2;

/* eMailClient des commandes qui ne sont pas en 2015 */
SELECT eMailClient
FROM Commande
MINUS
SELECT eMailClient
FROM Commande
WHERE to_char(dateCommande,'YYYY') = '2015'



SELECT eMailUtilisateur, nomClient, telClient
FROM (

    )


/*   10 . A   */

DROP VIEW v1;
DROP VIEW v2;
DROP VIEW v3;
DROP VIEW v4;
DROP VIEW v5;
DROP VIEW v6;

CREATE VIEW v1(idFormat) AS
SELECT idFormat FROM FormatPapier;

CREATE VIEW v2(idLaboratoire, idFormat) AS
SELECT idLaboratoire, idFormat FROM TarifFormat;

CREATE VIEW v3(idLaboratoire) AS
SELECT idLaboratoire
FROM v2
GROUP BY idLaboratoire
HAVING COUNT(*) in (SELECT COUNT(*) FROM v1);

CREATE VIEW v4(idLaboratoire) AS
SELECT idLaboratoire FROM TarifDegressif;

CREATE VIEW v5(idLaboratoire) AS
SELECT * FROM v4 
NATURAL JOIN v3;

CREATE VIEW v6(idLaboratoire, raisonSociale, eMailLabo) AS
SELECT idLaboratoire, raisonSociale, eMailLabo FROM Laboratoire;

SELECT raisonSociale, eMailLabo FROM v6 NATURAL JOIN v5;



/*   10 . B   */

CREATE VIEW v1(eMailUtilisateur) AS
SELECT eMailUtilisateur FROM Client WHERE region='Centre';

CREATE VIEW v2(numCommande, idLaboratoire, eMailClient) AS
SELECT numCommande, eMailClient FROM Commande WHERE to_char(dateCommande,'MMYYYY') = '052013';

CREATE VIEW v3(numCommande) AS
SELECT numCommande FROM v1
JOIN v2
ON v1.eMailUtilisateur=v2.eMailClient;

CREATE VIEW v4(idLaboratoire, numCommande) AS
SELECT Laboratoire.idLaboratoire, numCommande FROM Laboratoire 
JOIN Commande
ON Laboratoire.idLaboratoire=Commande.idLaboratoire;

CREATE VIEW v5(idLaboratoire, raisonSociale, eMailLabo) AS
SELECT idLaboratoire FROM v4
GROUP BY idLaboratoire
HAVING COUNT(*) IN (SELECT COUNT(*) FROM v3);

SELECT eMailLabo, raisonSociale
FROM v5
JOIN Laboratoire
ON v5.idLaboratoire=Laboratoire.idLaboratoire;



/*   11 . A   */

