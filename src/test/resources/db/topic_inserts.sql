ALTER SEQUENCE IF EXISTS kategorija_id_seq RESTART WITH 3;
INSERT INTO kategorija(id, naziv, kratica) VALUES (1, 'TEST1', 't1');
INSERT INTO kategorija(id, naziv, kratica) VALUES (2, 'TEST2', 't2');