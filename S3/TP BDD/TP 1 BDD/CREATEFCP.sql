DROP TABLE FCP CASCADE CONSTRAINTS;
DROP TABLE Action CASCADE CONSTRAINTS;
DROP TABLE Client CASCADE CONSTRAINTS;
DROP TABLE Region CASCADE CONSTRAINTS;
DROP TABLE ComposeDe CASCADE CONSTRAINTS;
DROP TABLE PossedeAction CASCADE CONSTRAINTS;
DROP TABLE PossedeFCP CASCADE CONSTRAINTS;

-- TABLE : FCP
CREATE TABLE FCP
  ( codeFCP 	NUMBER(4)  		NOT NULL
  , nomFCP 		VARCHAR2(20)  
  , dateDebut 	DATE  
  , dateFin 	DATE  
  , CONSTRAINT PK_FCP PRIMARY KEY (codeFCP)  
  ) ;

-- TABLE : Action
CREATE TABLE Action
  ( codeAct 		NUMBER(4)  		NOT NULL
  , nomAct 			VARCHAR2(20)  
  , valeurCourante 	NUMBER(8,2)  
  , codeRegion 		NUMBER(4)  		NOT NULL
  , CONSTRAINT PK_Action PRIMARY KEY (codeAct)  
  ) ;

-- TABLE : Client
CREATE TABLE Client
  ( numCli 					NUMBER(4)  		NOT NULL
  , prenomCli 				VARCHAR2(20)  
  , nomCli 					VARCHAR2(20)  
  , dateOuvertureCompte 	DATE  
  , CONSTRAINT PK_Client PRIMARY KEY (numCli)  
  ) ;

-- TABLE : Region
CREATE TABLE Region
  ( codeRegion 	NUMBER(4)  	NOT NULL
  , nomRegion 	VARCHAR2(20)  
  , CONSTRAINT PK_Region PRIMARY KEY (codeRegion)  
  ) ;

-- TABLE : ComposeDe
CREATE TABLE ComposeDe
  ( codeFCP 	NUMBER(4)  NOT NULL
  , codeAct 	NUMBER(4)  NOT NULL
  , quantite 	NUMBER(6)  
  , prixAchat 	NUMBER(8,2)  
  , CONSTRAINT PK_ComposeDe PRIMARY KEY (codeFCP, codeAct)  
  ) ;

-- TABLE : PossedeAction
CREATE TABLE PossedeAction
  ( numCli 		NUMBER(4)  	NOT NULL
  , codeAct 	NUMBER(4)  	NOT NULL
  , quantite 	NUMBER(6)  
  , prixAchat 	NUMBER(8,2)  
  , CONSTRAINT PK_PossedeAction PRIMARY KEY (numCli, codeAct)  
  ) ;

-- TABLE : PossedeFCP
CREATE TABLE PossedeFCP
  ( numCli 			NUMBER(4)  	NOT NULL
  , codeFCP 		NUMBER(4)  	NOT NULL
  , quantiteFCP 	NUMBER(6)  
  , CONSTRAINT PK_PossedeFCP PRIMARY KEY (numCli, codeFCP)  
   ) ;

-- CREATION DES REFERENCES DE TABLE
ALTER TABLE Action ADD (
    CONSTRAINT FK_Action_Region FOREIGN KEY (codeRegion)
        REFERENCES Region (codeRegion)) ;

ALTER TABLE ComposeDe ADD (
    CONSTRAINT FK_ComposeDe_FCP FOREIGN KEY (codeFCP)
        REFERENCES FCP (codeFCP)) ;

ALTER TABLE ComposeDe ADD (
    CONSTRAINT FK_ComposeDe_Action FOREIGN KEY (codeAct)
        REFERENCES Action (codeAct)) ;

ALTER TABLE PossedeAction ADD (
    CONSTRAINT FK_PossedeAction_Client FOREIGN KEY (numCli)
        REFERENCES Client (numCli)) ;

ALTER TABLE PossedeAction ADD (
    CONSTRAINT FK_PossedeAction_Action FOREIGN KEY (codeAct)
        REFERENCES Action (codeAct)) ;

ALTER TABLE PossedeFCP ADD (
    CONSTRAINT FK_PossedeFCP_Client FOREIGN KEY (numCli)
        REFERENCES Client (numCli)) ;

ALTER TABLE PossedeFCP ADD (
    CONSTRAINT FK_PossedeFCP_FCP FOREIGN KEY (codeFCP)
        REFERENCES FCP (codeFCP)) ;

