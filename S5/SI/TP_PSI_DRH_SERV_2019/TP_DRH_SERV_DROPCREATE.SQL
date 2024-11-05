DROP TABLE Serv  CASCADE CONSTRAINTS;
DROP TABLE Drh   CASCADE CONSTRAINTS;
-- -----------------------------------------------------------------------------
--       TABLE : Serv
-- -----------------------------------------------------------------------------
CREATE TABLE Serv (
	numServ		numBER(2)    NOT NULL
	, nomServ	VARCHAR2(14)
	, lieu		VARCHAR2(8)  
	, CONSTRAINT PK_Serv PRIMARY KEY (numServ)  
);
-- -----------------------------------------------------------------------------
--       TABLE : Drh
-- -----------------------------------------------------------------------------
CREATE TABLE Drh ( 
	mat			numBER(4)    NOT NULL
	, nom		VARCHAR2(10)
	, matChef	numBER(4)
	, emploi	VARCHAR2(15)
	, dateEmb	DATE
	, sal		numBER(7,2)
	, vac		numBER(7,2)
	, numServ	numBER(2)  
	, CONSTRAINT PK_Drh PRIMARY KEY (mat)  
);


