SELECT * FROM LesFilms;
SELECT * FROM LesActeurs;

/*   4   */

DROP VIEW c1;
DROP VIEW c2;
DROP VIEW c3;
DROP VIEW c4;

CREATE VIEW c1(titre) AS
SELECT titre FROM LesFilms WHERE realisateur='r1';

CREATE VIEW c2(acteurs) AS
SELECT acteurs FROM 
(
    SELECT titre,acteurs FROM LesActeurs
    NATURAL JOIN c1
);

CREATE VIEW c3(acteurs) AS
SELECT titre FROM LesActeurs WHERE acteurs='r1';

CREATE VIEW c4(acteurs) AS
SELECT acteurs FROM
(
    SELECT titre,acteurs FROM LesActeurs "LA2"
    NATURAL JOIN c3
);

SELECT * FROM c2 UNION SELECT * FROM c4;

/*   6   */

SELECT DISTINCT (lf1.realisateur)
FROM LesFilms LF1,LesFilms LF2,LesActeurs LA3
WHERE lf1.realisateur=la3.acteurs AND lf1.realisateur<>lf2.realisateur AND lf2.titre=la3.titre;

CREATE OR REPLACE VIEW q6(titre,realisateur,acteurs) AS
SELECT LesFilms.titre,LesFilms.realisateur,LesActeurs.acteurs
FROM LesFilms
JOIN LesActeurs
ON LesFilms.titre=LesActeurs.titre;

SELECT DISTINCT(LesFilms.realisateur)
FROM LesFilms, q6
WHERE q6.realisateur<>LesFilms.realisateur AND LesFilms.realisateur=q6.acteurs;


/*   8   */

CREATE OR REPLACE VIEW v1(titre) AS
SELECT titre FROM LesFilms WHERE realisateur='r1';

CREATE OR REPLACE VIEW v2(titre,acteurs) AS
SELECT LesActeurs.titre, LesActeurs.acteurs
FROM LesActeurs
JOIN v1
ON v1.titre = LesActeurs.titre;

SELECT DISTINCT(v2.acteurs)
FROM v2
GROUP BY acteurs
HAVING COUNT(*) IN (SELECT COUNT(*) FROM v1);

/*   9   */

SELECT realisateur FROM LesFilms
MINUS
SELECT lf1.realisateur FROM LesFilms lf1
JOIN LesActeurs
ON lf1.realisateur = LesActeurs.acteurs AND lf1.titre = LesActeurs.titre;

/*   10   */

CREATE OR REPLACE VIEW v1(titre,realisateur) AS
SELECT LesFilms.titre,LesFilms.realisateur
FROM LesFilms
JOIN LesActeurs
ON LesFilms.titre = LesActeurs.titre AND LesActeurs.acteurs = LesFilms.realisateur;

SELECT lf1.realisateur FROM LesFilms lf1
GROUP BY lf1.realisateur
HAVING COUNT(*) IN(
    SELECT COUNT(*)
    FROM v1
    WHERE v1.realisateur = lf1.realisateur
);

/*   11   */

