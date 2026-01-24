-----------------
----- EXO 1 -----
-----------------

-------------
----- 1 -----
-------------

SELECT * FROM R
UNION
SELECT * FROM S;

{(0,1),(2,3),(2,4),(3,4),(0,2),(2,5)}

-------------
----- 2 -----
-------------

SELECT * FROM R
UNION ALL
SELECT * FROM S;

{(0,1),(2,3),(0,1),(2,4),(3,4),(0,1),(2,4),(0,2),(2,5),(3,4),(3,4)}

-------------
----- 3 -----
-------------

SELECT * FROM R
INTERSECT
SELECT * FROM S;

{(0,1),(2,4),(3,4)}

-------------
----- 4 -----
-------------

SELECT * FROM R
INTERSECT ALL
SELECT * FROM S;

{(0,1),(2,4),(3,4)}

-------------
----- 5 -----
-------------

SELECT * FROM R
EXCEPT
SELECT * FROM S;

{(2,3)}

-------------
----- 6 -----
-------------

SELECT * FROM R
EXCEPT ALL
SELECT * FROM S;

{(0,1),(2,3)}

-------------
----- 7 -----
-------------

SELECT * FROM S
EXCEPT
SELECT * FROM R;

{(0,2),(2,5)}

-------------
----- 8 -----
-------------

SELECT * FROM S
EXCEPT ALL
SELECT * FROM R;

{(0,2),(2,5),(3,4)}

-------------
----- 9 -----
-------------

SELECT DISTINCT 
a+1 AS 'a', 
b-1 AS 'b' 
FROM S

{(1,0),(3,3),(1,1),(3,4),(4,3),(4,3)}

--------------
----- 10 -----
--------------

SELECT a,b FROM S
WHERE a<b AND (a+b>a*b OR a+b >= 6)

{(0,1),(0,1),(2,4),(3,4)}

--------------
----- 11 -----
--------------

SELECT a,b FROM S
WHERE a>1 OR b>4 OR b=2

{(2,3),(2,4),(3,4)}

--------------
----- 12 -----
--------------

SELECT R.a,R.b FROM (
    R JOIN S ON R.a<>S.a AND R.b<>S.b
)

{(0,1),(2,3),(2,4),(3,4)}

--------------
----- 13 -----
--------------

SELECT * FROM S
EXCEPT (
    SELECT a FROM S 
    JOIN 
    SELECT b FROM S
)


{}

--------------
----- 14 -----
--------------

SELECT R.a*R.a AS a, S.b*S.b AS b FROM (
    R JOIN S
    ON
    R.a>3 OR S.b>4 OR S.b=2
)

-- {
--     (0,1,0,2), (0,1,2,5),
--     (2,3,0,2), (2,3,2,5),
--     (0,1,0,2), (0,1,2,5),
--     (2,4,0,2), (2,4,2,5),
--     (3,4,0,2), (3,4,2,5),
-- }

{
    (0,4),(0,25),
    (4,4),(4,25),
    (9,4),(9,25),    
}


-----------------
----- EXO 2 -----
-----------------


-------------
----- 1 -----
-------------

R NATURAL JOIN S

{(0,1,2), (0,1,2), (0,1,2), (0,1,2), (2,3,5) (2,4,5), (3,4,5)}

-------------
----- 2 -----
-------------

S NATURAL JOIN T

{(1,2,3), (1,2,3), (2,5,5), (2,5,6), (3,5,5), (3,5,6), (4,5,5), (4,5,6)}

-------------
----- 3 -----
-------------

R NATURAL JOIN T

{}

-------------
----- 4 -----
-------------

R JOIN S ON R.b<S.b

{
    (0,1,2,5),(0,1,3,5)(0,1,4,5),(0,1,2,5),(0,1,3,5)(0,1,4,5),
    (2,3,4,5),
}

-------------
----- 5 -----
-------------

R JOIN T ON (R.a+T.d)=5

{
    (0,1,5,5),
    (0,1,5,5),
    (2,3,2,3),
    (2,4,2,3),
}

-------------
----- 6 -----
-------------

R JOIN T ON R.b=T.c

{
    (2,3,3,4),
}

-------------
----- 7 -----
-------------

SELECT COUNT(b) FROM R GROUP BY b

5

-------------
----- 8 -----
-------------

SELECT a,SUM(b) FROM R
GROUP BY b
ORDER BY a

{
    (0,2),(2,7),(3,4)
}

-------------
----- 9 -----
-------------

SELECT a,MIN(b) FROM S
GROUP BY a
ORDER BY a

{
    (0,1),(2,3),(3,4)
}

--------------
----- 10 -----
--------------

SELECT SUM(S.c) AS 'c',R.a AS 'a',R.b AS 'b' FROM 
(
    R JOIN S ON R.b<S.b
) GROUP BY c ORDER BY c,a,b

{
    (15,0,1),
    (5,2,3),
}

--------------
----- 11 -----
--------------

SELECT R.a AS 'a',T.d AS 'd',AVG(b+c) AS 'avg' FROM 
(
    R JOIN T ON (R.a+T.d)=5
) GROUP BY avg ORDER BY a,d,avg

{
    (0,5,6),
    (2,3,5.5),
}

--------------
----- 12 -----
--------------

SELECT b, MAX(R.a) AS 'a', MIN(T.c) AS 'c' FROM 
(
    SELECT a,b,c,MAX(d) FROM
    (
        SELECT * FROM R
        CROSS JOIN
        SELECT * FROM T
    )
    GROUP BY d
) GROUP BY a,c ORDER BY b,a,c

-- {
--     (0,1,5,6),(2,3,5,6),(2,4,5,6),(3,4,5,6)
-- }

{
    (3,5)
}

--------------
----- 13 -----
--------------

SELECT * FROM R ORDER BY b,a

{
    (3,4),(2,4),(2,3),(0,1),(0,1)
}

--------------
----- 13 -----
--------------

SELECT DISTINCT a,c FROM
    (
        SELECT * FROM T NATURAL JOIN 
        (
            SELECT a,c,SUM(b) AS "bSum" 
            FROM (SELECT * FROM R NATURAL JOIN (SELECT * FROM S)) 
            GROUP BY a,c
        ) 
    )
WHERE d>bSum
ORDER BY a

-- R NATURAL JOIN S
-- {(0,1,2), (2,3,5) (2,4,5), (3,4,5)}

-- SUM(b)
-- {0,2,1), (2,5,7), (3,5,4)}

-- T NATURAL JOIN xxx (format a,c,SUM(b), d
-- {(0,2,1,3), (2,5,7,5), (2,5,7,6), (3,5,4,5), (3,5,4,6)}

-- DISTINCT d>SUM(b)
-- {(0,2), (3,5)}


-----------------
----- EXO 3 -----
-----------------

-------------
----- 1 -----
-------------

πactor(σdirector='Lucas' (movie))

SELECT DISTINCT actor FROM movie WHERE director='Lucas'

-------------
----- 2 -----
-------------

πmovieGoer((seen)⨝(σdirector='Lucas' (movie)))

SELECT DISTINCT movieGoer FROM 
(
    SELECT * FROM seen
    NATURAL JOIN
    movie WHERE director='Lucas'
)

-------------
----- 3 -----
-------------

πmovieGoer(seen) ∩ πmovieGoer(likes)

SELECT DISTINCT movieGoer FROM seen
INTERSECT
SELECT DISTINCT movieGoer FROM likes

-------------
----- 4 -----
-------------

πmovieGoer (seen) ⨝ (likes)

SELECT DISTINCT movieGoer FROM 
(
    SELECT * FROM seen
    NATURAL JOIN
    SELECT * FROM likes
)

-------------
----- 5 -----
-------------

πmovieGoer ((likes) ÷ ((seen) ⨝ (likes)))

SELECT DISTINCT movieGoer FROM likes
MINUS
(
    SELECT * FROM seen
    NATURAL JOIN
    SELECT * FROM likes
)

-------------
----- 6 -----
-------------

σmovieSeen=(γcount(title)→totalMovies (movie)) (γmovieGoer,count(title)→moviesSeen(seen))

SELECT movieGoer FROM (
	SELECT DISTINCT movieGoer,title FROM seen
)  AS gens
GROUP BY title
HAVING COUNT(gens.title)=(SELECT DISTINCT * FROM movie AS mc WHERE gens.title=mc.tile)

-------------
----- 7 -----
-------------

πmovieGoer(σfilmsVu=filmsLike (γmovieGoer,count(filmsVu)→filmsVu (seen) ⨝ γmovieGoer,count(filmsLike)→filmsVu (likes)))

SELECT DISTINCT movieGoer FROM (
	SELECT DISTINCT movieGoer,title FROM seen
)  AS gens
GROUP BY title
HAVING COUNT(gens.title)=(SELECT DISTINCT * FROM movie AS mc WHERE gens.title=mc.tile)
