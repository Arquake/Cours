DELETE FROM Utilisateur where eMailUtilisateur='dupont@gmail.com';
DELETE FROM client where eMailUtilisateur='dupont@gmail.com';
/*
INSERT INTO Utilisateur (eMailUtilisateur) VALUES ('dupont@gmail.com');
INSERT INTO Client (eMailUtilisateur, nomClient, login, motDePasse, adressePostaleClient, region, telClient, quota) VALUES ('dupont@gmail.com', 'Dupont', 'Michel', 'MotDePasse','93270','Seine-St-Denis','0789687532', 30)

SELECT * FROM Utilisateur;
Select * from client
*/

/*
DELETE FROM Client WHERE eMailUtilisateur = 'dupont@gmail.com';
DELETE FROM Utilisateur WHERE eMailUtilisateur = 'dupont@gmail.com';

SELECT * FROM Utilisateur;
Select * from client
*/

/*
INSERT INTO Utilisateur (eMailUtilisateur) VALUES ('dupont@gmail.com');
INSERT INTO Client (eMailUtilisateur, nomClient, login, motDePasse, adressePostaleClient, region, telClient, quota) VALUES ('dupont@gmail.com', 'Dupont', 'Michel', 'MotDePasse','93270','Seine-St-Denis','0789687532', 30);
UPDATE Client SET motDePasse = 'AAA' where eMailUtilisateur = 'dupont@gmail.com';

SELECT * FROM Client
*/

/*
DROP TABLE Utilisateur CASCADE CONSTRAINTS;
*/

/*
ALTER TABLE Album ADD constraint chx_Album_date CHECK (dateCreation <= dateDerniereModification);
*/


