-- -----------------------------------------------------------------------------
--       CREATION DES REFERENCES DE TABLE
-- -----------------------------------------------------------------------------

ALTER TABLE Drh ADD (
      CONSTRAINT FK_Drh_Drh
           FOREIGN KEY (matChef)
               REFERENCES Drh (mat))   ;

ALTER TABLE Drh ADD (
      CONSTRAINT FK_Drh_Serv
          FOREIGN KEY (numServ)
                REFERENCES SERV (numServ))   ;
