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


ACCEPT P_num PROMPT 'num du pilote : ';
    
ACCEPT V_city PROMPT 'nom de la ville : ';


    
declare

    monPilote Pilote%rowtype;
    nbVol NUMBER;
    salaireAvant NUMBER;
    
begin

    SELECT * INTO monPilote FROM Pilote WHERE numPilote=&P_num;
    
    SELECT COUNT(*) INTO nbVol FROM Vol WHERE numPilote=&P_num AND villeArr='&V_city';
    
    dbms_output.put_line('Le pilote ' || monPilote.nomPilote || ' qui habite ' || monPilote.adresse || 'a fait ' || nbVol || ' vers la ville de ' || '&V_city');
    
    IF nbVol<10 THEN
        dbms_output.put_line('Le pilote est junior');
    ELSE
        dbms_output.put_line('Le pilote est senior');
        UPDATE Pilote SET salaire = monPilote.salaire * 1.10 WHERE numPilote=&P_num;
    END IF;
    SELECT salaire INTO salaireAvant FROM Pilote WHERE numPilote=&P_num;
    dbms_output.put_line(salaireAvant);

end;





----------------------



DROP TABLE Promotion;
DROP TABLE Drh;


CREATE TABLE Drh (
    id_drh NUMBER PRIMARY KEY,
    grade NUMBER(3),
    salaire NUMBER(6)
);

CREATE TABLE Promotion (
    id_promotion NUMBER PRIMARY KEY,
    salaire NUMBER(6),
    CONSTRAINT FK_idDrh_Promotion FOREIGN KEY (id_promotion) REFERENCES Drh(id_drh)
);

INSERT INTO Drh VALUES(1,3,1000);
INSERT INTO Drh VALUES(2,2,1100);
INSERT INTO Drh VALUES(3,3,1200);
INSERT INTO Drh VALUES(4,3,1300);

    
DECLARE
    CURSOR C1 IS SELECT id_drh,salaire FROM Drh WHERE grade=3;
    v_new_salaire NUMBER(6);
BEGIN
    FOR Emp in C1 LOOP
        IF Emp.salaire<1200 THEN Emp.salaire:=Emp.salaire*1.3;
        ELSE Emp.salaire:=Emp.salaire*1.1;
        END IF;
        INSERT INTO Promotion VALUES (Emp.id_drh, Emp.salaire);
    
    END LOOP;
END;
/
