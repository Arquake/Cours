DROP TABLE Formateur;
DROP TABLE Competence;
DROP TABLE Contrats;
DROP TABLE Formation;
DROP TABLE EnseignantSansContrat;

CREATE TABLE Formateur (
    id_Enseignant NUMBER(6) PRIMARY KEY,
    nom VARCHAR2(64),
    prenom VARCHAR2(64),
    salaire NUMBER(6,2),
    date_naiss DATE
);

CREATE TABLE EnseignantSansContrat(
    id_enseignant NUMBER(6) PRIMARY KEY
);

CREATE TABLE Competence (
    id_Enseignant NUMBER(6),
    matiere VARCHAR2(64),
    CONSTRAINT PK_id_matiere_Competence PRIMARY KEY (id_Enseignant, matiere)
);


CREATE TABLE Formation (
    id_Enseignant Number(6),
    matiere VARCHAR2(64),
    CONSTRAINT PK_id_matiere_Formation PRIMARY KEY (id_Enseignant, matiere)
);

CREATE TABLE Contrats (
    id_Enseignant Number(6),
    societe VARCHAR2(64),
    CONSTRAINT PK_id_societe_Contrats PRIMARY KEY (id_Enseignant, societe)
);


insert into formateur values (1,'n1' , 'p1', 2000, to_date('1980/01/01','YYYY/MM/DD'));
insert into formateur values (2,'n2' , 'p2', 3000, to_date('1980/01/01','YYYY/MM/DD'));
insert into formateur values (3,'n3' , 'p3', 2400.36, to_date('1980/01/01','YYYY/MM/DD'));
insert into formateur values (4,'n4' , 'p4', 1400.65, to_date('1980/01/01','YYYY/MM/DD'));

insert into contrats values (1,'IBM');
insert into contrats values (2,'IBM');
insert into contrats values (2,'SUN');
insert into contrats values (3,'IBM');


insert into competence values (1,'oracle');
insert into competence values (2,'oracle');
insert into competence values (3,'oracle');
insert into competence values (3,'java');



ACCEPT nomX PROMPT 'Nom du formateur';

DECLARE

    nb NUMBER;
    id_ens Formateur.id_enseignant%TYPE;
    nom_societe Contrats.societe%TYPE;
    CURSOR C(id_prof Formateur.id_enseignant%TYPE) IS SELECT DISTINCT societe FROM Contrats WHERE id_enseignant=id_prof;
    societes_trouves VARCHAR2(640) := '';

BEGIN

    SELECT id_enseignant INTO id_ens FROM Formateur WHERE formateur.nom='&nomX';
    SELECT COUNT(contrats.societe) INTO nb FROM Contrats WHERE id_enseignant=id_ens;
    
    IF nb=0 THEN 
        dbms_output.put_line('aucun contrat signé de la part de ' || '&nomX');
        INSERT INTO EnseignantSansContrat VALUES (id_ens);
    ELSIF nb=1 THEN
        SELECT UNIQUE societe INTO nom_societe FROM Contrats WHERE id_enseignant=id_ens;
        dbms_output.put_line('un seul contrat signé par ' || '&nomX' ||' avec la société ' || nom_societe);
    ELSE
        FOR societe_lines IN C(id_ens) LOOP
            societes_trouves := societes_trouves ||  societe_lines.societe || '-' ;
        END LOOP;
        dbms_output.put_line('Le formateur ' || '&nomX' || ' a signé des contrats avec : ' || societes_trouves);
    END IF;
END;
/


---------------------------------------------------------------------------------


DECLARE

    CURSOR C IS SELECT DISTINCT id_enseignant FROM Contrats;
    CURSOR C2 IS SELECT DISTINCT id_Enseignant FROM Formateur MINUS (
    SELECT DISTINCT id_enseignant FROM Contrats
    );
    number_of_contracts NUMBER(10);

BEGIN

    FOR prof IN C LOOP
        SELECT Count(*) INTO number_of_contracts FROM Contrats
        WHERE Contrats.id_enseignant=prof.id_enseignant;
        dbms_output.put_line(number_of_contracts);
        IF number_of_contracts>30 THEN
            UPDATE Formateur SET salaire=(
                SELECT salaire*1.3 FROM Formateur 
                WHERE Formateur.id_enseignant=prof.id_enseignant
            )
            WHERE Formateur.id_enseignant=prof.id_enseignant;
        ELSIF number_of_contracts>20 THEN
            UPDATE Formateur SET salaire=(
                SELECT salaire*1.2 FROM Formateur 
                WHERE Formateur.id_enseignant=prof.id_enseignant
            )
            WHERE Formateur.id_enseignant=prof.id_enseignant;
        ELSIF number_of_contracts>10 THEN
            UPDATE Formateur SET salaire=(
                SELECT salaire*1.1 FROM Formateur 
                WHERE Formateur.id_enseignant=prof.id_enseignant
            )
            WHERE Formateur.id_enseignant=prof.id_enseignant;
        END IF;
    END LOOP;
    
    FOR ENS IN C2 LOOP
        UPDATE Formateur SET salaire=(
            SELECT salaire*.9 FROM Formateur 
            WHERE Formateur.id_enseignant=ENS.id_enseignant
        )
        WHERE Formateur.id_enseignant=ENS.id_enseignant;
    END LOOP;
    
END;
/

--------------------------------------------------------------------




ACCEPT X1 PROMPT 'société 1 : ';
ACCEPT X2 PROMPT 'société 2 : ';

DECLARE

    CURSOR C(
        X Contrats.societe%TYPE,
        Y Formation.matiere%TYPE,
        Z Formation.matiere%TYPE
    ) 
    IS
    SELECT id_Enseignant, nom FROM Formateur 
    NATURAL JOIN 
    (
        SELECT DISTINCT id_Enseignant FROM Competence WHERE matiere=X
        MINUS SELECT DISTINCT id_Enseignant FROM Competence WHERE matiere=Y
    );

BEGIN

   FOR noms IN C('s', '&X1', '&X2') LOOP
        dbms_output.put_line('ici');
        dbms_output.put_line(noms.nom);
   END LOOP;
    
END;
/



----------------------------------------------------


ACCEPT X1 PROMPT 'société 1 : ';
ACCEPT X2 PROMPT 'société 2 : ';

DECLARE

    CURSOR C(
        X Contrats.societe%TYPE,
        Y Formation.matiere%TYPE,
        Z Formation.matiere%TYPE
    ) 
    IS
    SELECT id_Enseignant, nom FROM Formateur 
    NATURAL JOIN 
    (
        SELECT DISTINCT id_Enseignant FROM Competence WHERE matiere=Y
        MINUS SELECT DISTINCT id_Enseignant FROM Competence WHERE matiere=Z
    )
    NATURAL JOIN
    (SELECT id_Enseignant FROM Contrats WHERE societe=X);
    
    Y Competence.matiere%TYPE := 'oracle';
    Z Competence.matiere%TYPE := 'java';
    
    s1 NUMBER :=0;
    s2 NUMBER :=0;
    
    TYPE societeTable IS TABLE OF Formateur.nom%TYPE INDEX BY BINARY_INTEGER;
    listeSociete1 societeTable;
    listeSociete2 societeTable;

BEGIN


   FOR noms IN C('&X1', Y, Z) LOOP
        listeSociete1(s1) := noms.nom;
        s1:=s1+1;
   END LOOP;
   
   FOR noms IN C('&X2', Y, Z) LOOP
        listeSociete2(s2) := noms.nom;
        s2:=s2+1;
   END LOOP;
   
   IF s1 = 0 THEN
        dbms_output.put_line('la société &X1 n a pas d employé correspondant à la demande');
    ELSE
        dbms_output.put_line('la société &X1 à comme employés :');
        FOR n IN 0..s1-1 LOOP
            dbms_output.put_line(listeSociete1(n));
        END LOOP;
    END IF;
    
    IF s2 = 0 THEN
        dbms_output.put_line('la société &X2 n a pas d employé correspondant à la demande');
    ELSE
        dbms_output.put_line('la société &X2 à comme employés :');
        FOR n IN 0..s2-1 LOOP
            dbms_output.put_line(listeSociete2(n));
        END LOOP;
    END IF;
    
    IF s1 < s2 THEN
        dbms_output.put_line(' La société &X2 a plus de contrats que la société &X1');
    ELSIF s2 = s1 THEN
        dbms_output.put_line(' La société &X1 a autant contrats que la société &X2');
    ELSE
        dbms_output.put_line(' La société &X1 a plus de contrats que la société &X2');
    END IF;
END;
/