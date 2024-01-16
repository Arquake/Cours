INSERT INTO Client VALUES(1, 'Pierre',  'Leloup', to_date('22/12/2000','DD/MM/YYYY'));
INSERT INTO Client VALUES(2, 'Paul',    'Durand', to_date('12/01/1998','DD/MM/YYYY'));
INSERT INTO Client VALUES(3, 'Louis',   'Dupont', to_date('15/03/2001','DD/MM/YYYY'));
INSERT INTO Client VALUES(4, 'Jacques', 'Martin', to_date('28/09/2001','DD/MM/YYYY'));
INSERT INTO Client VALUES(5, 'Pierre',  'Perrin', to_date('11/06/2000','DD/MM/YYYY'));
-- INSERT INTO Client VALUES(6, 'Aurélia',  'Dubois', to_date('13/01/2011','DD/MM/YYYY'));

INSERT INTO Region VALUES(1, 'Europe');
INSERT INTO Region VALUES(2, 'USA');

INSERT INTO Action VALUES(1, 'Alcatel',          5.1,  1);
INSERT INTO Action VALUES(2, 'Snecma',           19.3, 1);
INSERT INTO Action VALUES(3, 'General Electric', 95.6, 2);
INSERT INTO Action VALUES(4, 'BNP',              11.8, 1);
INSERT INTO Action VALUES(5, 'IBM',              21.3, 2);

INSERT INTO FCP 
	VALUES (1, 'MAXITUNE'	, to_date('15/01/2000','DD/MM/YYYY')
							, to_date('14/01/2016','DD/MM/YYYY'));
INSERT INTO FCP 
	VALUES (2, 'PEPERE'		, to_date('28/03/1999','DD/MM/YYYY')
							, to_date('27/03/2015','DD/MM/YYYY'));
INSERT INTO FCP 
	VALUES(3, 'DYNAMIQUE'	, to_date('01/04/2001','DD/MM/YYYY')
							, to_date('31/03/2015','DD/MM/YYYY'));

INSERT INTO PossedeAction VALUES(1, 1, 100,   10.1);
INSERT INTO PossedeAction VALUES(1, 2, 1000,  5.6);
INSERT INTO PossedeAction VALUES(1, 3, 220,   20.5);
INSERT INTO PossedeAction VALUES(2, 1, 134,   20);
INSERT INTO PossedeAction VALUES(2, 5, 213,   15.3);
INSERT INTO PossedeAction VALUES(3, 1, 24434, 18);
INSERT INTO PossedeAction VALUES(3, 2, 112,   13.6);
INSERT INTO PossedeAction VALUES(3, 4, 6000,  6.1);
INSERT INTO PossedeAction VALUES(4, 3, 1000,  80.6);
INSERT INTO PossedeAction VALUES(5, 3, 123,   75.1);
INSERT INTO PossedeAction VALUES(5, 5, 500,   14.9);
-- INSERT INTO PossedeAction VALUES(6, 2, 100000,   10.3);

INSERT INTO ComposeDe VALUES (1, 1, 10, 12.7);
INSERT INTO ComposeDe VALUES (1, 2, 15, 5.2);
INSERT INTO ComposeDe VALUES (1, 4, 12, 18.4);
INSERT INTO ComposeDe VALUES (2, 1, 3,  22.1);
INSERT INTO ComposeDe VALUES (2, 2, 5,  21);
INSERT INTO ComposeDe VALUES (2, 3, 1,  10);
INSERT INTO ComposeDe VALUES (2, 4, 20, 12.4);
INSERT INTO ComposeDe VALUES (3, 3, 12, 68.1);
INSERT INTO ComposeDe VALUES (3, 5, 5,  15.3);

INSERT INTO PossedeFCP VALUES(1, 1, 50);
INSERT INTO PossedeFCP VALUES(1, 2, 75);
INSERT INTO PossedeFCP VALUES(2, 1, 50);
INSERT INTO PossedeFCP VALUES(4, 3, 100);

SELECT * FROM Client;

SELECT * FROM Region;

SELECT * FROM Action;

SELECT * FROM FCP;

SELECT * FROM PossedeAction;

SELECT * FROM ComposeDe;

SELECT * FROM PossedeFCP;
