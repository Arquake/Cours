
-- (0)
CREATE TABLE FormatPapier (
    idFormat                    VARCHAR2(8) NOT NULL 
				CONSTRAINT PK_FormatPapier PRIMARY KEY
    , libelleFormat             VARCHAR2(32)
    , largeur                   NUMBER(6,2)
    , hauteur                   NUMBER(6,2)
    );

CREATE TABLE Laboratoire (
    idLaboratoire               VARCHAR2(8) NOT NULL
    				CONSTRAINT PK_Laboratoire PRIMARY KEY  
    , raisonSociale             VARCHAR2(32)
    , adressePostaleLabo        VARCHAR2(128)
    , eMailLabo                 VARCHAR2(64)
    , telLabo                   VARCHAR2(10)  
);

CREATE TABLE TypePapier (
    idTypePapier                VARCHAR2(8) NOT NULL
  	  			CONSTRAINT PK_TypePapier PRIMARY KEY 
    , libelleTypePapier         VARCHAR2(32)  
);

CREATE TABLE Utilisateur (
    eMailUtilisateur            VARCHAR2(64)  NOT NULL
    				CONSTRAINT PK_Utilisateur PRIMARY KEY  
);

-- (1)
CREATE TABLE Client (
    eMailUtilisateur            VARCHAR2(64)  NOT NULL
				CONSTRAINT PK_Client PRIMARY KEY 
				CONSTRAINT FK_Client_Utilisateur REFERENCES Utilisateur (eMailUtilisateur)
    , nomClient                 VARCHAR2(32)
    , login                     VARCHAR2(16)
    , motDePasse                VARCHAR2(16)
    , adressePostaleClient      VARCHAR2(128)
    , region                    VARCHAR2(32)
    , telClient                 VARCHAR2(10)
    , quota                     NUMBER(4)  
    );

CREATE TABLE TarifDegressif (
    idLaboratoire               VARCHAR2(8) NOT NULL
    				CONSTRAINT FK_TarifDegressif_Laboratoire REFERENCES Laboratoire (idLaboratoire)
    , quantiteMini              NUMBER(4)   NOT NULL
    , quantiteMaxi              NUMBER(4)   NOT NULL
    , reduction                 NUMBER(4,2)  
    , CONSTRAINT PK_TarifDegressif PRIMARY KEY (quantiteMini, quantiteMaxi, idLaboratoire)  
    );

CREATE TABLE TarifFormat (
    idLaboratoire               VARCHAR2(8) NOT NULL
				CONSTRAINT FK_TarifFormat_Laboratoire REFERENCES Laboratoire (idLaboratoire)
    , idFormat                  VARCHAR2(8) NOT NULL
    				CONSTRAINT FK_TarifFormat_FormatPapier  REFERENCES FormatPapier (idFormat)
    , idTypePapier              VARCHAR2(8) NOT NULL
    				CONSTRAINT FK_TarifFormat_TypePapier REFERENCES TypePapier (idTypePapier)
    , prixUnitaire              NUMBER(4,2)  
    , CONSTRAINT PK_TarifFormat PRIMARY KEY (idLaboratoire, idFormat, idTypePapier)   
);

-- (2)
CREATE TABLE Album (
    idAlbum                     NUMBER(4)   NOT NULL
				CONSTRAINT PK_Album PRIMARY KEY 
    , titreAlbum                VARCHAR2(32)
    , dateCreation              DATE
    , dateDerniereModification  DATE
    , visibilite                VARCHAR2(32)
       	 			CONSTRAINT CK_Album_visibilite CHECK (visibilite IN ('Public', 'Privé'))
    , eMailClient               VARCHAR2(64) NOT NULL
    				CONSTRAINT FK_Album_Client REFERENCES Client (eMailUtilisateur)
    );

CREATE TABLE CarteBancaire (
    numCarteBancaire            VARCHAR2(16)  NOT NULL
				CONSTRAINT PK_CarteBancaire PRIMARY KEY 
    , dateLimiteValidite        DATE
    , codeControle              NUMBER(3)
    , typeCarteBancaire         VARCHAR2(32)
    , eMailClient               VARCHAR2(64)  NOT NULL
  	  			CONSTRAINT FK_CarteBancaire_Client REFERENCES Client (eMailUtilisateur)
    );

CREATE TABLE Commande (
    numCommande                 VARCHAR2(8)  NOT NULL
				CONSTRAINT PK_Commande PRIMARY KEY 
    , dateCommande              DATE
    , adresseLivraison          VARCHAR2(128)
    , etatCommande              VARCHAR2(32)
      	 			CONSTRAINT CK_Commande_etat  CHECK (etatCommande IN ('Attente Paiement' , 'Attente Impression', 'Attente Accusé réception', 'Livrée'))
    , eMailClient               VARCHAR2(64)  NOT NULL
				CONSTRAINT FK_Commande_Client REFERENCES Client (eMailUtilisateur)
    , idLaboratoire             VARCHAR2(8)  NOT NULL
    				CONSTRAINT FK_Commande_Laboratoire REFERENCES Laboratoire (idLaboratoire)
    );

-- (3)
CREATE TABLE Image (
    idAlbum                     NUMBER(4)  NOT NULL
    				CONSTRAINT FK_Image_Album REFERENCES Album (idAlbum)
    , numImage                  NUMBER(4)  NOT NULL
    , titreImage                VARCHAR2(32)
    , taille                    NUMBER(4)
    , annotation                VARCHAR2(64)
    , dateTelechargement        DATE  
    , CONSTRAINT PK_Image PRIMARY KEY (idAlbum, numImage)
    );

CREATE TABLE Tirage (
    numCommande                 VARCHAR2(8) NOT NULL
    				CONSTRAINT FK_Tirage_Commande REFERENCES Commande (numCommande)
    , numTirage                 NUMBER(4)   NOT NULL
    , idFormat                  VARCHAR2(8) NOT NULL
    				CONSTRAINT FK_Tirage_FormatPapier REFERENCES FormatPapier (idFormat)
    , idTypePapier              VARCHAR2(8)  NOT NULL
    				CONSTRAINT FK_Tirage_TypePapier REFERENCES TypePapier (idTypePapier)
    , CONSTRAINT PK_Tirage PRIMARY KEY (numCommande, numTirage)
    );

-- (4)
CREATE TABLE Notation (
    eMailUtilisateur            VARCHAR2(64)  NOT NULL
    				CONSTRAINT FK_Notation_Utilisateur REFERENCES Utilisateur (eMailUtilisateur)
    , idAlbum                   NUMBER(4)     NOT NULL
    , numImage                  NUMBER(4)     NOT NULL
    , note                      NUMBER(2)
    , commentaire               VARCHAR2(64)
    , dateNotation              DATE  
    , CONSTRAINT PK_Notation PRIMARY KEY (eMailUtilisateur, idAlbum, numImage)
    , CONSTRAINT FK_Notation_Image FOREIGN KEY (idAlbum, numImage) REFERENCES Image (idAlbum, numImage)
   );

CREATE TABLE TirageImages (
    numCommande                   VARCHAR2(8) NOT NULL
    , numTirage                   NUMBER(4)   NOT NULL
    , idAlbum                     NUMBER(4)   NOT NULL
    , numImage                    NUMBER(4)   NOT NULL
    , quantite                    NUMBER(4)  
    , CONSTRAINT PK_TirageImages 
        PRIMARY KEY (numCommande, numTirage, idAlbum, numImage)
    , CONSTRAINT FK_TirageImages_Image
        FOREIGN KEY (idAlbum, numImage)
        REFERENCES Image (idAlbum, numImage)
    , CONSTRAINT FK_TirageImages_Tirage
        FOREIGN KEY (numCommande, numTirage)
        REFERENCES Tirage (numCommande, numTirage)
);
