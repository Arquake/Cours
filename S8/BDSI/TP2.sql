-- 1

SELECT COUNT(*) FROM pokemon

-- 2

SELECT DISTINCT type FROM pokemon

-- 3

SELECT * FROM pokemon WHERE type = 'INSECT' OR type = 'insect'

-- 4

SELECT * FROM pokemon WHERE attaques LIKE '%Ecume%'

-- 5

SELECT * FROM pokemon EXCEPT SELECT * FROM pokemon WHERE attaques LIKE '%Ecume%'

-- 6

SELECT * FROM pokemon WHERE attaques LIKE '%Ecume%' OR attaques LIKE '%Surf%'

-- 7

SELECT * FROM pokemon WHERE attaques LIKE '%Ecume%' AND attaques LIKE '%Surf%'

-- 8

SELECT * FROM pokemon WHERE attaques LIKE '%Ecume%' AND attaques NOT LIKE '%Surf%'

-- 9

SELECT COUNT(*) FROM pokemon WHERE poids>=100

-- 10 

SELECT AVG(taille) FROM pokemon WHERE poids>=100

-- 11

SELECT * FROM pokemon WHERE sousevolution IS null

-- 12

SELECT DISTINCT p1.id,p1.nom FROM (
	(SELECT id,nom,sousevolution, type FROM pokemon "p1" WHERE sousevolution IS NOT null) "p1"
	JOIN pokemon "p2"
	ON p1.sousevolution=p2.nom AND p1.type<>p2.type
)

-- 13

SELECT * FROM (
	SELECT p2id, p2nom, COUNT(p2id) "compte" FROM (
		SELECT DISTINCT p1.id "p1id",p1.nom "p1nom", p2.id "p2id", p2.nom "p2nom" FROM (
			(SELECT id,nom,sousevolution, type FROM pokemon "p1" WHERE sousevolution IS NOT null) "p1"
			JOIN pokemon "p2"
			ON p1.sousevolution=p2.nom AND p1.type<>p2.type
		)
	) GROUP BY p2id, p2nom
) GROUP BY p2id,p2nom, compte HAVING compte=MAX(compte)

-- 14

