-- 5
SELECT * FROM airport WHERE city = 'Springfield'

-- 6

SELECT DISTINCT origin_airport FROM (
	(SELECT * FROM flights WHERE departure_delay < 0)
	NATURAL JOIN 
	(SELECT * FROM airport)
)

-- 7

SELECT * FROM flights
JOIN
(SELECT iata_code FROM airport WHERE city='San Diego') AS airports
ON
flights.origin_airport=airports.iata_code

-- 8

SELECT * FROM flights WHERE 
(origin_airport IN (SELECT iata_code FROM airport WHERE city='San Diego') AND destination_airport IN (SELECT iata_code FROM airport WHERE city='Springfield'))
OR
(origin_airport IN (SELECT iata_code FROM airport WHERE city='Springfield') AND destination_airport IN (SELECT iata_code FROM airport WHERE city='San Diego'))

-- 9

SELECT * FROM flights WHERE 
diverted AND cancelled

-- 10

SELECT DISTINCT airline FROM flights WHERE 
(origin_airport IN (SELECT iata_code FROM airport WHERE city='San Diego') OR destination_airport IN (SELECT iata_code FROM airport WHERE city='San Diego'))

-- 11

SELECT DISTINCT airline FROM flights WHERE 
(origin_airport IN (SELECT iata_code FROM airport WHERE city='San Diego') OR destination_airport IN (SELECT iata_code FROM airport WHERE city='San Diego'))
EXCEPT
(SELECT DISTINCT airline FROM flights WHERE 
(origin_airport IN (SELECT iata_code FROM airport WHERE city='San Jose') OR destination_airport IN (SELECT iata_code FROM airport WHERE city='San Jose')))

-- 12

SELECT * FROM airport
EXCEPT 
(SELECT * FROM flights WHERE departure_delay>=0)
