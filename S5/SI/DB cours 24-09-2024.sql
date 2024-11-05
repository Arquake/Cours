DROP TABLE Vol;
DROP TABLE Pilote;
DROP TABLE Avion;


CREATE TABLE Pilote (
    numPilote NUMBER(4),
    nomPilote VARCHAR2(20),
    adresse VARCHAR2(20),
    salaire NUMBER(6),
    CONSTRAINT PK_Pilote PRIMARY KEY (numPilote)
);



CREATE TABLE Avion (
    numAvion NUMBER(6),
    capacite NUMBER(3),
    localisation VARCHAR2(20),
    CONSTRAINT PK_numAvion PRIMARY KEY (numAvion)
);


CREATE TABLE Vol (
    numVol VARCHAR2(6),
    villeDep VARCHAR2(20),
    villeArr VARCHAR2(20),
    heureDep DATE,
    heureArr DATE,
    numPilote NUMBER(4),
    numAvion NUMBER(6),
    CONSTRAINT PK_Vol PRIMARY KEY (numVol),
    CONSTRAINT FK_Vol_numPilote_Pilote FOREIGN KEY (numPilote) REFERENCES Pilote(numPilote),
    CONSTRAINT FK_Vol_numAvion_Avion FOREIGN KEY (numAvion) REFERENCES Avion(numAvion)
    
);

INSERT INTO Pilote VALUES (1, 'Michel', 'Orléans',3000);
INSERT INTO Pilote VALUES (2, 'Daniel', 'Paris', 2650);
INSERT INTO Pilote VALUES (3, 'Pierre', 'Bordeaux', 2700);

INSERT INTO Avion VALUES (1, 300, 'Nice');
INSERT INTO Avion VALUES (2, 460, 'Toulouse');
INSERT INTO Avion VALUES (3, 420, 'Paris');

INSERT INTO Vol VALUES (1, 'Bordeaux', 'Nice', TO_DATE('10:05', 'hh:mi'), TO_DATE('11:30', 'hh:mi'), 1, 2);
INSERT INTO Vol VALUES (2, 'Paris', 'Nice', TO_DATE('10:05', 'hh:mi'), TO_DATE('11:30', 'hh:mi'), 1, 1);
INSERT INTO Vol VALUES (3, 'Bordeaux', 'Nice', TO_DATE('10:05', 'hh:mi'), TO_DATE('11:30', 'hh:mi'), 2, 1);
INSERT INTO Vol VALUES (4, 'Toulouse', 'Nice', TO_DATE('10:05', 'hh:mi'), TO_DATE('11:30', 'hh:mi'), 2, 2);
INSERT INTO Vol VALUES (5, 'Bordeaux', 'Paris', TO_DATE('10:05', 'hh:mi'), TO_DATE('11:30', 'hh:mi'), 1, 3);
INSERT INTO Vol VALUES (6, 'Paris', 'Nice', TO_DATE('10:05', 'hh:mi'), TO_DATE('11:30', 'hh:mi'), 3, 3);
INSERT INTO Vol VALUES (7, 'Bordeaux', 'Nice', TO_DATE('10:05', 'hh:mi'), TO_DATE('11:30', 'hh:mi'), 1, 2);
INSERT INTO Vol VALUES (8, 'Bordeaux', 'Nice', TO_DATE('10:05', 'hh:mi'), TO_DATE('11:30', 'hh:mi'), 1, 2);
INSERT INTO Vol VALUES (9, 'Bordeaux', 'Nice', TO_DATE('10:05', 'hh:mi'), TO_DATE('11:30', 'hh:mi'), 1, 2);
INSERT INTO Vol VALUES (10, 'Bordeaux', 'Nice', TO_DATE('10:05', 'hh:mi'), TO_DATE('11:30', 'hh:mi'), 1, 2);
INSERT INTO Vol VALUES (11, 'Bordeaux', 'Nice', TO_DATE('10:05', 'hh:mi'), TO_DATE('11:30', 'hh:mi'), 1, 2);
INSERT INTO Vol VALUES (12, 'Bordeaux', 'Nice', TO_DATE('10:05', 'hh:mi'), TO_DATE('11:30', 'hh:mi'), 1, 2);
INSERT INTO Vol VALUES (13, 'Bordeaux', 'Nice', TO_DATE('10:05', 'hh:mi'), TO_DATE('11:30', 'hh:mi'), 1, 2);
INSERT INTO Vol VALUES (14, 'Bordeaux', 'Nice', TO_DATE('10:05', 'hh:mi'), TO_DATE('11:30', 'hh:mi'), 1, 2);
INSERT INTO Vol VALUES (15, 'Bordeaux', 'Nice', TO_DATE('10:05', 'hh:mi'), TO_DATE('11:30', 'hh:mi'), 1, 2);


declare

    CURSOR C IS SELECT * FROM Avion WHERE localisation='Paris' FOR UPDATE OF localisation;
    
begin

    FOR avion_select IN C LOOP
        UPDATE Avion set localisation='Nice' WHERE CURRENT OF C;
    END LOOP;

end;


--------------------------------------------------------


CREATE OR REPLACE FUNCTION nb_de_vol (nom_P VARCHAR2) RETURN NUMBER
    AS
        nb NUMBER ;
    BEGIN
        SELECT Count(*) INTO nb FROM Vol WHERE numPilote=(
            SELECT numPilote FROM Pilote WHERE nomPilote=nom_P
        );
        RETURN (nb) ;
    END;

DECLARE

    ACC    
    
BEGIN

    dbms_output.put_line(nb_de_vol('Michel'));

END;


----------------------------------------------------


CREATE OR REPLACE FUNCTION nvVolAvion (num_P NUMBER, num_A NUMBER) RETURN NUMBER
    AS
        nb NUMBER ;
    BEGIN
        SELECT Count(*) INTO nb FROM Vol WHERE numPilote=num_P AND numAvion=num_A;
        RETURN (nb) ;
    END;/

CREATE OR REPLACE FUNCTION nP (nom_P VARCHAR2) RETURN NUMBER
    AS
        nb NUMBER;
        BEGIN
            SELECT Count(*) into nb FROM Pilote WHERE nomPilote=nom_P;
            IF nb > 1 THEN RETURN -1;
            ELSEIF nb=0 THEN RETURN 0;
            ELSE RETURN nb;
            END IF; 
        END;/


ACCEPT nom_P PROMPT 'nom du pilote : '; 
ACCEPT num_A PROMPT 'numéro de l avion : ';  

DECLARE

      num_du_pilote Pilote.numPilote%TYPE;
      nb_de_vols NUMBER;
    
BEGIN

    num_du_pilote:=nP(nom_P);
    IF num_du_pilote = -1 THEN
        dbms_output.put_line('Attention ce pilote a des homonymes');
    ELSEIF num_du_pilote=0 THEN
        dbms_output.put_line('Pilote non connu');
    ELSEIF nbVolAvion(num_du_pilote,num_A)<10 THEN
        DELETE FROM Pilote WHERE numPilote=nP(nom_P)

END;


----------------------------------------------------


DROP TABLE historique_Drh;
DROP TABLE Drh;

CREATE TABLE Drh (
    id_drh NUMBER(3) PRIMARY KEY,
    grade NUMBER(3),
    salaire NUMBER(6)
);

CREATE TABLE historique_Drh (
    type VARCHAR2(32),
    date_modif DATE
);

INSERT INTO Drh(id_drh, grade, salaire) VALUES(1,3,1000);
INSERT INTO Drh(id_drh, grade, salaire) VALUES(2,2,1100);
INSERT INTO Drh(id_drh, grade, salaire) VALUES(3,3,1200);
INSERT INTO Drh(id_drh, grade, salaire) VALUES(4,3,1300);

SELECT * FROM historique_Drh;


Create Or Replace TRIGGER Drh_histo
AFTER INSERT OR UPDATE OR DELETE ON Drh
FOR EACH ROW
Begin
    If DELETING Then Insert into historique_Drh values (' Suppression ', SYSDATE) ;
    ELSIF UPDATING Then Insert into historique_Drh values (' MAJ ', SYSDATE) ;
    ELSE Insert into historique_Drh values (' Insertion ', SYSDATE) ;
    END If;
End;


-----------------------------------------


CREATE OR REPLACE TRIGGER suppressionPilote
AFTER DELETE ON Pilote

DECLARE

    pilote_restant NUMBER;

BEGIN
    SELECT Count(*) INTO pilote_restant FROM Pilote;
    dbms_output.put_line('Nombre de pilote restant ' || pilote_restant);
END;/


------------------------------------------



CREATE OR REPLACE TRIGGER Pilote_maj_sup
AFTER DELETE OR UPDATE ON Pilote
FOR EACH ROW

DECLARE

    nb_vols NUMBER := 0;

BEGIN

    IF DELETING THEN 
        SELECT Count(*) INTO nb_vols FROM Vol WHERE numPilote=:Old.numPilote;
        dbms_output.put_line('Le Pilote' || :Old.nomPilote || 'a fait' || nb_vols);
    ELSE IF INSERTING THEN
        dbms_output.put_line('un nouveau pilote a été inséré avec numPilote ' || :New.numPilote);
    END IF;    
END;/


-------------------------------------------


CREATE OR REPLACE VIEW ViewVolAv(numVol,villeDep,villeArr,heureDep,heureArr,numPilote,numAvion,capacite,localisation); #pas complet

CREATE OR REPLACE TRIGGER inserer_vol_av
INSTEAD OF INSERT ON ViewVolAv

BEGIN

    INSERT INTO Vol Values(
    :New.numVol,
    :New.villeDep,
    :New.villeArr,
    :New.heureDep,
    :New.heureArr,
    :New.numPilote,
    :New.numAvion
    );
    
    INSERT INTO Avion Values (
        :New.numAvion,
        :New.capacite,
        :New.localisation
    );
       
END;/



---------------------------------------------------


