/*   1   */

SELECT prenomCli AS prenom ,nomCli AS nom FROM Client ORDER BY prenomCli,nomCli ASC

/*   2   */

SELECT codeAct,nomAct,valeurCourante,codeRegion FROM Action

/*   3   */

DROP VIEW v1;
DROP VIEW v2;

CREATE VIEW v1(code,nom,valeurCourante,codeRegion) AS
SELECT codeAct,nomAct,valeurCourante,codeRegion FROM Action
WHERE valeurCourante = (SELECT MAX(valeurCourante) FROM Action);

CREATE VIEW v2(codeAct,nomAct,valeurCourante,codeRegion) AS
SELECT codeAct,nomAct,valeurCourante,codeRegion FROM Action
WHERE valeurCourante = (SELECT MIN(valeurCourante) FROM Action);

(SELECT * FROM v1) UNION (SELECT * FROM v2)

/*   4   */

DROP VIEW v1;
DROP VIEW v2;

CREATE VIEW v1(numCli) AS
SELECT numCli FROM Client
WHERE prenomCli='Pierre' AND nomCli='Leloup';

CREATE VIEW v2(codeAct) AS
SELECT codeAct FROM PossedeAction
WHERE numCli=(Select * FROM v1);

SELECT * FROM Action WHERE codeAct IN (SELECT * FROM v2);


SELECT * FROM Action
NATURAL JOIN PossedeAction
NATURAL JOIN (SELECT nomCli FROM Client WHERE prenomCli='Pierre' AND nomCli='Leloup');


CREATE OR REPLACE VIEW v1(numCli,codeAct) AS
SELECT numCli,codeAct FROM PossedeAction WHERE PossedeAction.numCli=
(
    SELECT numCli FROM Client WHERE prenomCli='Pierre' AND nomCli='Leloup'
);

SELECT * FROM Action JOIN v1 ON Action.codeAct=v1.codeAct;

/*   5   */

SELECT SUM(quantite*(valeurCourante-prixAchat)) "Gain Total Potentiel"
FROM Action NATURAL JOIN PossedeAction
NATURAL JOIN (SELECT numCli FROM Client WHERE prenomCli='Pierre' AND nomCli='Leloup');

/*   6   */

SELECT codeFCP,SUM(quantite*(valeurCourante-prixAchat)) "Gain Potentiel Par FCP"
FROM Action NATURAL JOIN ComposeDe
GROUP BY codeFCP;

/*   7   */

CREATE OR REPLACE VIEW v1(codeFCP,valeur) AS
SELECT codeFCP,SUM(quantite*(valeurCourante-prixAchat)) "Gain Potentiel Par FCP"
FROM Action NATURAL JOIN ComposeDe
GROUP BY codeFCP;

SELECT codeFCP "FCP Le Plus Performant",valeur "GAIN" FROM v1 WHERE valeur = (SELECT MAX(valeur) FROM v1);
 
SELECT codeFCP "FCP Le Moins Performant",valeur "GAIN" FROM v1 WHERE valeur = (SELECT MIN(valeur) FROM v1);

/*   8   */

CREATE OR REPLACE VIEW v1(codeFCP,valeur) AS
SELECT codeFCP,SUM(quantite*(valeurCourante-prixAchat)) "Gain Potentiel Par FCP"
FROM Action NATURAL JOIN ComposeDe
GROUP BY codeFCP;

SELECT ROUND(AVG(valeur),2) "Perf moyenne des FCP" FROM v1;

/*   9   */

CREATE OR REPLACE VIEW GainAction(GainTotalAction) AS
SELECT SUM(quantite*(valeurCourante-prixAchat)) "Gain Total Potentiel"
FROM Action NATURAL JOIN PossedeAction
NATURAL JOIN (SELECT numCli FROM Client WHERE prenomCli='Pierre' AND nomCli='Leloup');

CREATE OR REPLACE VIEW GainFCP(codeFCP,Gain) AS
SELECT codeFCP,SUM(quantite*(valeurCourante-prixAchat))
FROM ComposeDe
NATURAL JOIN Action
GROUP BY codeFCP;

CREATE OR REPLACE VIEW GainFCPPL(GainTotalFCP) AS
SELECT SUM( gain * quantiteFCP )
FROM GainFCP
NATURAL JOIN PossedeFCP
NATURAL JOIN (SELECT numCli FROM Client WHERE prenomCli='Pierre' AND nomCli='Leloup');

SELECT GainTotalAction+GainTotalFCP FROM GainAction CROSS JOIN GainFCPPL;

/* OU */ 

SELECT GainTotalAction+GainTotalFCP FROM GainAction,GainFCPPL;

/*   10   */

CREATE OR REPLACE VIEW GainAction(numCli,Gain) AS
SELECT numCli, sum(quantite*(valeurCourante))
FROM Action NATURAL JOIN PossedeAction
GROUP BY numCli;

CREATE OR REPLACE VIEW GainFCP(codeFCP,Gain) AS
SELECT codeFCP,SUM(quantite*(valeurCourante))
FROM ComposeDe
NATURAL JOIN Action
GROUP BY codeFCP;

CREATE OR REPLACE VIEW GAINFCPTotal(numCli,Gain) AS
SELECT numCli, sum(gain * quantiteFCP)
FROM GainFCP
NATURAL JOIN PossedeFCP
GROUP BY numCli;

CREATE OR REPLACE VIEW GainTotal(numCli,gain) AS
SELECT numCli,SUM(Gain) FROM (
    SELECT * FROM GainAction
    UNION SELECT * FROM GAINFCPTotal
)
GROUP BY numCli;

CREATE OR REPLACE VIEW cli(numCli) AS
SELECT numCli FROM GainTotal
WHERE  gain = (SELECT MAX(gain) FROM GainTotal);

SELECT numCli,prenomCli,nomCli,dateOuvertureCompte FROM Client
NATURAL JOIN cli;

/*   11   */

CREATE OR REPLACE VIEW GainAction(numCli,Gain) AS
SELECT numCli, sum(quantite*(valeurCourante-prixAchat))
FROM Action NATURAL JOIN PossedeAction
GROUP BY numCli;

CREATE OR REPLACE VIEW GainFCP(codeFCP,Gain) AS
SELECT codeFCP,SUM(quantite*(valeurCourante-prixAchat))
FROM ComposeDe
NATURAL JOIN Action
GROUP BY codeFCP;

CREATE OR REPLACE VIEW GAINFCPTotal(numCli,Gain) AS
SELECT numCli, sum(gain * quantiteFCP)
FROM GainFCP
NATURAL JOIN PossedeFCP
GROUP BY numCli;

CREATE OR REPLACE VIEW GainTotal(numCli,gain) AS
SELECT numCli,SUM(Gain) FROM (
    SELECT * FROM GainAction
    UNION SELECT * FROM GAINFCPTotal
)
GROUP BY numCli;

CREATE OR REPLACE VIEW cli(numCli) AS
SELECT numCli FROM GainTotal
WHERE  gain = (SELECT MAX(gain) FROM GainTotal);

SELECT numCli,prenomCli,nomCli,dateOuvertureCompte FROM Client
NATURAL JOIN cli;

/*   12   */

CREATE OR REPLACE VIEW GainAction(numCli,Gain) AS
SELECT numCli, sum(quantite*(valeurCourante-prixAchat))
FROM Action NATURAL JOIN PossedeAction
GROUP BY numCli;

CREATE OR REPLACE VIEW GainFCP(codeFCP,Gain) AS
SELECT codeFCP,SUM(quantite*(valeurCourante-prixAchat))
FROM ComposeDe
NATURAL JOIN Action
GROUP BY codeFCP;

CREATE OR REPLACE VIEW GAINFCPTotal(numCli,Gain) AS
SELECT numCli, sum(gain * quantiteFCP)
FROM GainFCP
NATURAL JOIN PossedeFCP
GROUP BY numCli;

CREATE OR REPLACE VIEW GainTotal(numCli,gain) AS
SELECT numCli,SUM(Gain) FROM (
    SELECT * FROM GainAction
    UNION SELECT * FROM GAINFCPTotal
)
GROUP BY numCli;


CREATE OR REPLACE VIEW dureeCompte(numCli,duree) AS
SELECT numCli, TO_CHAR(SYSDATE,'YYYY')-TO_CHAR(dateOuvertureCompte,'YYYY') FROM Client;


CREATE OR REPLACE VIEW RendementTotal(numCli,rendement) AS
SELECT numCli,gain/duree FROM GainTotal
NATURAL JOIN dureeCompte;


CREATE OR REPLACE VIEW cli(numCli) AS
SELECT numCli FROM RendementTotal
WHERE rendement = (SELECT MAX(rendement) FROM RendementTotal);

SELECT numCli,prenomCli,nomCli,dateOuvertureCompte FROM Client
NATURAL JOIN cli;

/*   13   */


SELECT * FROM FCP WHERE TO_CHAR(dateDebut,'YYYY') = '2000';

/*   14   */

SELECT codeFCP,nomFCP FROM FCP WHERE DateFin = (SELECT MIN(dateFin) FROM FCP);
/* ou */
SELECT codeFCP,nomFCP FROM FCP WHERE DateFin <= ALL(SELECT dateFin FROM FCP);

/*   15   */

CREATE OR REPLACE VIEW v1(codeAct) AS
SELECT codeAct FROM Action
NATURAL JOIN (SELECT codeRegion FROM Region WHERE nomRegion='Europe');

CREATE OR REPLACE VIEW v2(codeFCP) AS
SELECT codeFCP FROM ComposeDe
WHERE codeAct NOT IN (SELECT * FROM v1);

SELECT * FROM FCP WHERE FCP.codeFCP NOT IN (
    SELECT codeFCP FROM FCP
    NATURAL JOIN ComposeDe
    NATURAL JOIN Action
    NATURAL JOIN (
        SELECT CodeRegion FROM Region
        WHERE nomRegion<>'Europe'
    )
);

/*   16   */

CREATE OR REPLACE VIEW v1(numCli) AS
SELECT numCli FROM PossedeAction WHERE codeAct IN (
    SELECT codeAct FROM Action
    NATURAL JOIN (SELECT codeRegion FROM Region WHERE nomRegion<>'Europe')
);

CREATE OR REPLACE VIEW v2(numCli) AS
SELECT numCli FROM PossedeFCP WHERE codeFCP IN (
    SELECT codeFCP FROM FCP
    NATURAL JOIN ComposeDe
    NATURAL JOIN Action
    NATURAL JOIN (
        SELECT CodeRegion FROM Region
        WHERE nomRegion<>'Europe'
    )
);

SELECT * FROM Client
WHERE numCli NOT IN (
    SELECT * FROM v1 UNION SELECT * FROM v2
);