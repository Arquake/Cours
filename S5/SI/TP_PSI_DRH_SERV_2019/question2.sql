CREATE OR REPLACE FUNCTION NbEmploye
(
    p_nService SERV.NUMSERV%type
)
    RETURN NUMBER
AS
    nb NUMBER;

BEGIN
    SELECT COUNT(*)
    INTO nb
    FROM DRH
    WHERE NUMSERV=p_nService;

    return nb;
end;
/


DECLARE

BEGIN
    dbms_output.put_line(NbEmploye(1));
END;


----------------------------------------



CREATE OR REPLACE FUNCTION NbFormateur
(
    p_nService SERV.NUMSERV%type
)
    RETURN NUMBER
AS
    nb NUMBER;

BEGIN
    SELECT COUNT(*) INTO nb FROM DRH WHERE NUMSERV=p_nService AND emploi='FORMATEUR';

    return nb;
end;
/

BEGIN
    dbms_output.put_line(NbFormateur(1));
END;



-----------------------------------------


DECLARE 
    CURSOR C1 IS SELECT numserv FROM SERV FOR UPDATE;
    currentNbPers NUMBER := 0;
    currentNbForm NUMBER := 0;
BEGIN
    FOR s IN C1 LOOP
        currentNbForm:=NbFormateur(s.numserv);
        currentNbPers:=NbEmploye(s.numserv);
        UPDATE SERV SET NBFORM=currentNbForm, NBPERS=currentNbPers WHERE CURRENT OF C1;
    END LOOP;
END;
/


-------------------------------------------


CREATE OR REPLACE TRIGGER drh_mod
AFTER INSERT OR
DELETE OR
UPDATE
ON
DRH
FOR EACH ROW
DECLARE
    formateur NUMBER:=0;
    ancienFormateur NUMBER:=0;
    currentServ SERV%ROWTYPE;
BEGIN
    IF INSERTING THEN
        IF :NEW.emploi='FORMATEUR' THEN
            formateur:=1;
        ELSE
            formateur:=0;
        END IF;
        SELECT * INTO currentServ FROM SERV WHERE numserv=:NEW.numserv;
        UPDATE SERV SET NBPERS=(currentServ.NBPERS+1), NBFORM=(currentServ.NBFORM+formateur) WHERE numserv=:NEW.numserv;
        
    ELSIF DELETING THEN
        IF :NEW.emploi='FORMATEUR' THEN
            formateur:=1;
        ELSE
            formateur:=0;
        END IF;
        SELECT * INTO currentServ FROM SERV WHERE numserv=:OLD.numserv;
        UPDATE SERV SET NBPERS=(currentServ.NBPERS-1), NBFORM=(currentServ.NBFORM-formateur) WHERE numserv=:OLD.numserv;
    ELSIF UPDATING THEN
    
        IF :NEW.emploi='FORMATEUR' THEN
            formateur:=1;
        ELSE
            formateur:=0;
        END IF;
        
        IF :OLD.emploi='FORMATEUR' THEN
            ancienFormateur:=-1;
        ELSE
            ancienFormateur:=0;
        END IF;
        
        
        IF :NEW.numserv<>:OLD.numserv THEN
         UPDATE SERV SET NBPERS=(currentServ.NBPERS-1), NBFORM=(currentServ.NBFORM-ancienFormateur) WHERE numserv=:OLD.numserv;
         UPDATE SERV SET NBPERS=(currentServ.NBPERS+1), NBFORM=(currentServ.NBFORM+formateur) WHERE numserv=:NEW.numserv;
        ELSIF :NEW.emploi<>:OLD.emploi THEN
            UPDATE SERV SET NBFORM=(currentServ.NBFORM-ancienFormateur) WHERE numserv=:OLD.numserv;
        END IF;
    END IF;
END;
/


--------------------------------------


CREATE OR REPLACE FUNCTION MasseSalariale(ns NUMBER) RETURN NUMBER
AS
    total_sum NUMBER := 0;
BEGIN
    SELECT NVL(SUM(sal), 0) INTO total_sum FROM DRH WHERE numserv = ns;
    RETURN total_sum;
END;



DECLARE

BEGIN
    dbms_output.put_line(MasseSalariale(2));
END;


-----------------------------------------


CREATE OR REPLACE FUNCTION Budget(ns NUMBER) RETURN NUMBER
AS
    total_sum NUMBER := 0;
    vacc NUMBER:=0;
BEGIN
    SELECT NVL(SUM(sal), 0) INTO total_sum FROM DRH WHERE numserv = ns;
    SELECT NVL(SUM(vac), 0) INTO vacc FROM DRH WHERE numserv = ns;
    RETURN total_sum + vacc;
END;



DECLARE
    CURSOR C IS SELECT * FROM SERV FOR UPDATE;
BEGIN
    FOR item IN C LOOP
        UPDATE SERV SET budget=Budget(item.numserv) WHERE CURRENT OF C;
    END LOOP;
END;


--------------------------------------------


-- j'en saute une nsm



--------------------------------------------

