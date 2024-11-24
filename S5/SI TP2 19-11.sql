-------------------------------- 1
-- oracle
CREATE USER U3 IDENTIFIED by "123";
CREATE TABLE t(
    a NUMBER(2)
);
INSERT INTO T VALUES(0);
GRANT CONNECT, RESOURCE TO U3;
GRANT ALL on t TO U3;

-------------------------------- 2
-- oracle
INSERT INTO t VALUES(-1);
--bloqué en RX la table

-------------------------------- 3
-- U3
LOCK TABLE SYSTEM.t IN EXCLUSIVE MODE NOWAIT;
-- refus car table vérouillé en RX

-------------------------------- 4

-- U3
LOCK TABLE SYSTEM.t IN ROW SHARE MODE;
-- pas de pb car RX autorise le bloquage RS demandé dans la vie de cette transaction
-- t1 voit le -1 et t2 toujours pas

-------------------------------- 5

-- U3
INSERT INTO SYSTEM.t VALUES(-2);

-------------------------------- 6

-- U3
UPDATE SYSTEM.t SET A=3 WHERE A=0;

-------------------------------- 7

-- oracle
UPDATE t SET A=4 WHERE A=0;
-- en attente car U3 est entrain de modifier la table pour A=0

-------------------------------- 8

-- U3
COMMIT;
-- U3 voit -2 et 3 et oracle est débloqué mais la mise à jour a=4 ne se fait pas car il n'y a plus de ligne avec x=0
-- t1 (3,-1,-2) | t2(-2, 3)

-------------------------------- 9

-- oracle
COMMIT;
-- t1 et t2 voient -1,-2 et 3


-------------------------------- 10

-- oracle
SELECT * FROM t WHERE A=-1 FOR UPDATE;
-- donc la ligne A=-1 est bloquée

-------------------------------- 11

-- U3
UPDATE TABLE SYSTEM.t SET A=3 WHERE A=-1;
-- t2 est en attente à cause de la ligne précédente (10)

-------------------------------- 12

-- oracle
UPDATE t SET A=0 WHERE A=-1;
-- t1 voit -2,3,0 et t2 est toujours bloquée et voit -2,3,-1

-------------------------------- 13

-- oracle
COMMIT;
-- t2 est débloquée mais sa mise à jour ne passe pas car il n'y a plus de A=-1 dans la table t
-- les deux voient -2,3,0

-------------------------------- 14

--U3
COMMIT;
-- les deux voient -2,3,0

-------------------------------- 15

-- U3
LOCK TABLE SYSTEM.t IN SHARE MODE;

-------------------------------- 16

-- oracle
LOCK TABLE t IN EXCLUSIVE MODE NOWAIT;
-- ça ne passe pas car la table t est bloquée en S

-------------------------------- 17

-- oracle
UPDATE t SET A=-1 WHERE A=2;
-- mise en attente, car on a bloquée la table t en share mode

-------------------------------- 18

-- U3
UPDATE SYSTEM.t SET A=2 WHERE A=-2;
-- ça passe, t1 est toujours bloquée et t2 voit 2,3,0

-------------------------------- 19

-- U3
COMMIT;
-- t1 est débloquée et sa mise à jour passe
-- t2 voit 2,3,0 et t1 voit -1,3,0

--------------------------------- 20

-- oracle
COMMIT;
-- les deux voient -1,3,0

--------------------------------- 21

-- U3
LOCK TABLE SYSTEM.t IN SHARE ROW EXCLUSIVE MODE;

--------------------------------- 22

-- oracle
LOCK TABLE t IN ROW SHARE MODE NOWAIT;

--------------------------------- 23

-- oracle
INSERT INTO t VALUES(5)
-- Mise en attente car le mode SRX empêche les écritues

--------------------------------- 24

-- U3
DELETE FROM SYSTEM.t
-- t2 ne voit plus rien et t1 voit -1,3,0

--------------------------------- 25

-- U3
COMMIT;
-- t1 est débloquée et exécute son insertion mais seul t1 la voit et t2 ne voit rien

--------------------------------- 26

-- oracle
ROLLBACK;
-- t1 et t2 ne voient rien dans t

--------------------------------- 27

-- U3
LOCK TABLE SYSTEM.t IN SHARE MODE;

--------------------------------- 28

-- U3
INSERT INTO SYSTEM.t VALUES(-1);
-- seul t2 le voit et t1 voit rien

--------------------------------- 29

-- oracle
DROP TABLE t;
-- erreur affichée et pas de mise en attente

--------------------------------- 30

-- U3
ALTER TABLE SYSTEM.t ADD(b NUMBER(2));
-- Transaction auto-committed
-- donc le -1 dans A et null dans b st visible par t1 et t2
-- commit aussi les valeurs

--------------------------------- 31

-- U3
ALTER TABLE SYSTEM.t ADD(CONSTRAINT PK_t_a PRIMARY KEY(A));
-- A exécuter dans oracle
-- problème de GRANT

--------------------------------- 32

-- oracle
DROP TABLE t;
-- la table t est éffacée, elle n'existe plus ni dans t1 ni dans t2
-- Commit implicite (auto committed)

--------------------------------- 33

-- U3
DROP TABLE SYSTEM.t;
-- erreur car la table n'existe plus