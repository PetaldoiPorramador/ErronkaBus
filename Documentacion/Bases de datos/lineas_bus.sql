USE `ErronkaBus` ;

INSERT INTO Cliente VALUES ('78938427N', 'Manolo García Gómez', MD5('contramanolo'));
INSERT INTO Cliente VALUES ('73173892J', 'Miguel López Santiago', MD5('contramiguel'));
INSERT INTO Cliente VALUES ('28467283E', 'Ana Muñoz Ibañez', MD5('contraana'));
INSERT INTO Cliente VALUES ('19436725T', 'Elena Milán García', MD5('contraelena'));
INSERT INTO Cliente VALUES ('78955162A', 'Alberto Espino Sousa', MD5('contraalberto'));

#linea1
INSERT INTO Linea VALUES (1, 'BILBAO-Güeñes', '7:35', '22:05', '6:35', '21:05', 2.55, 30);

INSERT INTO Municipio VALUES (1, 'Bilbao');#48010 cod postal san mames
INSERT INTO Municipio VALUES (2, 'Güeñes');#48840

INSERT INTO CodigoPostal VALUES ('48010', 1);
INSERT INTO CodigoPostal VALUES ('48840', 2);
INSERT INTO CodigoPostal VALUES ('48840', 2);
INSERT INTO CodigoPostal VALUES ('48840', 2);
INSERT INTO CodigoPostal VALUES ('48840', 2);
INSERT INTO CodigoPostal VALUES ('48840', 2);
INSERT INTO CodigoPostal VALUES ('48840', 2);
INSERT INTO CodigoPostal VALUES ('48840', 2);

INSERT INTO Parada VALUES (1, 1, 'Bilbao Intermodal', 2, 'Gurtubay', 1,'48010');
INSERT INTO Parada VALUES (1, 2, 'Iorgi', 3, 'Iorgi', 24,'48840');
INSERT INTO Parada VALUES (1, 3, 'Sodupe Estación', 1, 'Urrestieta', 4,'48840');
INSERT INTO Parada VALUES (1, 4, 'Zeribai', 3, 'Zeribai', 31,'48840');
INSERT INTO Parada VALUES (1, 5, 'Artxube', 3, 'Artxube', 33,'48840');
INSERT INTO Parada VALUES (1, 6, 'Lambarri', 2, 'Lambarri', 5,'48840');
INSERT INTO Parada VALUES (1, 7, 'Lasier', 1, 'Lasier', 35,'48840');
INSERT INTO Parada VALUES (1, 8, 'Güeñes Ayuntamiento', -1, 'Enkarterri', 14,'48840');

INSERT INTO Billete VALUES (1, '2021-12-23', '32748572E', 1, 3, 7);

#linea2
INSERT INTO Linea VALUES (2, 'Barakaldo-UPV/EHU ', '7:35', '17:35', '11:05', '21:35', 1.35, 30);

INSERT INTO Municipio VALUES (3, 'Barakaldo');
INSERT INTO Municipio VALUES (4, 'Leioa');

INSERT INTO CodigoPostal VALUES ('48903', 3);
INSERT INTO CodigoPostal VALUES ('48903', 3);
INSERT INTO CodigoPostal VALUES ('48903', 3);
INSERT INTO CodigoPostal VALUES ('48903', 3);
INSERT INTO CodigoPostal VALUES ('48903', 4);

INSERT INTO Parada VALUES (2, 1, 'Balejo (Gurutzeta/Cruces)', 2, 'Balejo', 4172, '48903');
INSERT INTO Parada VALUES (2, 2, 'Argenta', 2, 'Argenta', 0399, '48903');
INSERT INTO Parada VALUES (2, 3, 'Lutxana (Eliza/Iglesia)', 1, 'Andikollano', 0405, '48903');
INSERT INTO Parada VALUES (2, 4, 'Landeta (Goikoa)', 3, 'Landeta', 649, '48903');
INSERT INTO Parada VALUES (2, 5, 'EHU Oteiza plaza', -1, 'Unibertsitateko errepidea', null, '48903');

INSERT INTO Billete VALUES (2, '2021-12-23', '19436725T', 2, 1, 5);