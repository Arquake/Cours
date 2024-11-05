CREATE OR REPLACE VIEW v_Image AS
SELECT * FROM IMAGE;

-- #1

CREATE OR REPLACE FUNCTION NbAlbumDispo(emailClientP Album.eMailClient%TYPE) RETURN NUMBER
AS
    res NUMBER;
BEGIN
    SELECT COUNT(*) INTO res FROM Album WHERE emailClient=emailClientP AND visibilite='Public';
    return res;
END;
/


DECLARE

BEGIN
    dbms_output.put_line(NbAlbumDispo('u1@'));

END;
/



-- #2

CREATE OR REPLACE FUNCTION NbImage(emailClientP Album.eMailClient%TYPE) RETURN NUMBER
AS
    res NUMBER;
BEGIN
    SELECT COUNT(*) INTO res FROM (
        SELECT * FROM Album NATURAL JOIN v_Image WHERE emailClient=emailClientP
    );
    return res;
END;
/


DECLARE

BEGIN
    dbms_output.put_line(NbImage('u1@'));

END;
/


-- #3

CREATE OR REPLACE FUNCTION Occupe(emailClientP Album.eMailClient%TYPE) RETURN NUMBER
AS
    res NUMBER;
BEGIN
    SELECT SUM(taille) INTO res FROM (
        SELECT * FROM Album NATURAL JOIN v_Image WHERE emailClient=emailClientP
    );
    IF res is NULL THEN
        res:=0;
    END IF;
    return res;
END;
/


DECLARE

BEGIN
    dbms_output.put_line(Occupe('u1@'));

END;
/

-- #4

ALTER TABLE Client ADD (
nombreImage NUMBER(4),
espaceStockageDispo NUMBER(4)
);

SELECT * FROM CLIENT;

DECLARE
    CURSOR C IS SELECT emailUtilisateur, quota FROM Client FOR UPDATE OF nombreImage,espaceStockageDispo;
    nbImg NUMBER;
    espaceDisp NUMBER;
BEGIN
    FOR ele IN C LOOP
        nbImg := NbImage(ele.eMailUtilisateur);
        UPDATE Client set nombreImage=nbImg, espaceStockageDispo=(ele.quota-Occupe(ele.eMailUtilisateur)) WHERE CURRENT OF C;
    END LOOP;
END;


-- #5

Insert Into Image Values (11,3,'i11_4',2000,NULL,SYSDATE);

CREATE OR REPLACE TRIGGER triggerSizeMaxCheck BEFORE INSERT ON IMAGE FOR EACH ROW
DECLARE
    email Client.emailUtilisateur%TYPE;
    stkrestant NUMBER;
BEGIN
    SELECT emailClient INTO email FROM ALBUM WHERE idAlbum=:NEW.idAlbum;
    SELECT espaceStockageDispo INTO stkrestant FROM Client WHERE emailUtilisateur=email;
    IF :NEW.taille > stkrestant THEN
        RAISE_APPLICATION_ERROR(-20001, 'Not enough storage space');
    ELSE
        dbms_output.put_line('success');
    END IF;
END;


-- #6


CREATE OR REPLACE TRIGGER modifyClientAfterImage
AFTER INSERT ON Image
FOR EACH ROW
DECLARE
    StockageDispo NUMBER(4) := 0;
    nbr_image NUMBER(4) :=0;
BEGIN
    SELECT C.espaceStockageDispo, c.nombreImage
    INTO StockageDispo, nbr_image 
    FROM CLIENT C 
    JOIN Album A ON C.eMailUtilisateur = A.eMailClient
    WHERE A.idAlbum = :new.idAlbum;
    
    
    UPDATE Client 
    SET espaceStockageDispo = espaceStockageDispo - :new.taille, nombreimage = nombreimage + 1
    WHERE eMailUtilisateur = (SELECT A.eMailClient FROM Album A WHERE A.idAlbum = :new.idAlbum);
    
END;
/


-- #7


CREATE OR REPLACE TRIGGER tarifDegTrigger BEFORE INSERT ON TarifDegressif
DECLARE
    num NUMBER.
BEGIN
    SELECT COUNT(*) INTO FROM TarifDegressif WHERE
    (quantiteMini<:NEW.quantiteMini AND
    quantiteMaxi>:NEW.quantiteMini) OR
    (quantiteMini<:NEW.quantiteMaxi AND
    quantiteMaxi>:NEW.quantiteMaxi);
    IF 
END;/