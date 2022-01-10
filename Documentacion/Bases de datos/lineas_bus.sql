USE `ErronkaBus` ;

INSERT INTO Cliente VALUES ('32748572S', 'Manolo García Gómez', 'contramanolo');
INSERT INTO Cliente VALUES ('73173892H', 'Miguel López Santiago', 'contramiguel');
INSERT INTO Cliente VALUES ('28467283J', 'Ana Muñoz Ibañez', 'contraana');
INSERT INTO Cliente VALUES ('19436725W', 'Elena Milán García', 'contraelena');
INSERT INTO Cliente VALUES ('28635618B', 'Alberto Espino Sousa', 'contraalberto');

#linea1
INSERT INTO Linea VALUES (1, 'BILBAO-Güeñes', '7:35', '22:05', '6:35', '21:05', 2.55, 30);

INSERT INTO Municipio VALUES (1, 'Bilbao');#48010 cod postal san mames
INSERT INTO Municipio VALUES (2, 'Güeñes');#48840

INSERT INTO Calle VALUES ('Gurtubay', '48010', 1);
INSERT INTO Calle VALUES ('Iorgi', '48840', 2);
INSERT INTO Calle VALUES ('Urrestieta', '48840', 2);
INSERT INTO Calle VALUES ('Zeribai', '48840', 2);
INSERT INTO Calle VALUES ('Artxube', '48840', 2);
INSERT INTO Calle VALUES ('Lambarri', '48840', 2);
INSERT INTO Calle VALUES ('Lasier', '48840', 2);
INSERT INTO Calle VALUES ('Enkarterri', '48840', 2);

INSERT INTO Parada VALUES (1, 1, 'Bilbao Intermodal', 1, 'Gurtubay');
INSERT INTO Parada VALUES (1, 2, 'Iorgi', 48045024, 'Iorgi');
INSERT INTO Parada VALUES (1, 3, 'Sodupe Estación', 48045004, 'Urrestieta');
INSERT INTO Parada VALUES (1, 4, 'Zeribai', 48045031, 'Zeribai');
INSERT INTO Parada VALUES (1, 4, 'Artxube', 48045033, 'Artxube');
INSERT INTO Parada VALUES (1, 5, 'Lambarri', 48045005, 'Lambarri');
INSERT INTO Parada VALUES (1, 6, 'Lasier', 48045035, 'Lasier');
INSERT INTO Parada VALUES (1, 7, 'Güeñes Ayuntamiento', 48045014, 'Enkarterri');

INSERT INTO Billete VALUES (1, '2021-12-23', '32748572S', 1, 3, 7);

#linea2
INSERT INTO Linea VALUES (2, 'Barakaldo-UPV/EHU ', '7:35', '17:35', '11:05', '21:35', 1.35, 30);

INSERT INTO Municipio VALUES (3, 'Barakaldo');
INSERT INTO Municipio VALUES (4, 'Leioa');

INSERT INTO Calle VALUES ('Balejo', '48903', 3);
INSERT INTO Calle VALUES ('Argenta', '48903', 3);
INSERT INTO Calle VALUES ('Andikollano', '48903', 3);
INSERT INTO Calle VALUES ('Landeta', '48903', 3);
INSERT INTO Calle VALUES ('Unibertsitateko errepidea', '48903', 4);

INSERT INTO Parada VALUES (2, 1, 'Balejo (Gurutzeta/Cruces)', 4172, 'Balejo');
INSERT INTO Parada VALUES (2, 2, 'Argenta', 0399, 'Argenta');
INSERT INTO Parada VALUES (2, 3, 'Lutxana (Eliza/Iglesia)', 0405, 'Andikollano');
INSERT INTO Parada VALUES (2, 4, 'Landeta (Goikoa)', 649, 'Landeta');
INSERT INTO Parada VALUES (2, 5, 'EHU Oteiza plaza', 1, 'Unibertsitateko errepidea');

INSERT INTO Billete VALUES (2, '2021-12-23', '19436725W', 2, 1, 5);