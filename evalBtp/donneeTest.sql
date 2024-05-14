-- donnees test

INSERT INTO utilisateur (mail, password, role, etat) VALUES ('root@gmail.com', 'root', 10, 0);


-- Données de test pour la table finitions
INSERT INTO finition (nom, marge, etat) 
VALUES 
('Standard', 0, 0),
('Gold', 5, 0),
('Premium', 7.5, 0),
('VIP', 10, 0);

-- Données de test pour la table unites
INSERT INTO unite (nom, etat) 
VALUES 
('m2', 0),
('m3', 0),
('fft', 0);

-- Données de test pour la table type_travaux
INSERT INTO type_travaux (code, nom, etat) 
VALUES 
('000', 'Travaux préparatoires', 0),
('100', 'Travaux de terrassement', 0),
('200', 'Travaux en Infrastructure', 0);

-- Données de test pour la table travauxs
INSERT INTO travaux (id_type_travaux, code, nom_travaux, id_unite, prix_unitaire, etat) 
VALUES 
(1, '001', 'Mur de soutainement', 1, 190000, 0),
(2, '101', 'Décapage des terrains meubles', 2, 3072.87, 0),
(2, '102', 'Travaux 3', 3, 3736.26, 0),
(2, '103', 'Travaux 4', 3, 9390.93, 0),
(2, '104', 'Travaux 5', 3, 37563.26, 0),
(2, '105', 'Travaux 6', 3, 152656, 0),
(3, '201', 'Travaux 7', 3, 172114.40, 0),
(3, '202', 'Travaux 8', 3, 573215.80, 0),
(3, '202', 'Travaux 9', 3,  573215.80, 0),
(3, '202', 'Travaux 10', 3, 573215.80, 0),
(3, '203', 'Travaux 11', 3, 37563.26, 0),
(3, '204', 'Travaux 12', 3, 73245.40, 0),
(3, '205', 'Travaux 13', 3, 487815.80, 0),
(3, '206', 'Travaux 14', 3, 33566.54, 0);

-- Données de test pour la table maisons
INSERT INTO maison (nom, description, etat, duree_construction) 
VALUES 
('Maison 1', 'Description de la maison 1', 0, 320),
('Maison 2', 'Description de la maison 2', 0, 460),
('Maison 3', 'Description de la maison 3', 0, 660);

INSERT INTO maison_travaux (id_maison, id_travaux, quantite) 
VALUES 
(1, 1, 2),
(1, 2, 1),
(1, 3, 4),
(1, 4, 3),
(2, 5, 2),
(2, 6, 1),
(2, 7, 4),
(2, 8, 3),
(3, 9, 2),
(3, 10, 1),
(3, 11, 4),
(3, 12, 3),
(3, 13, 2),
(3, 14, 1);

-- Données de test pour la table deviss
INSERT INTO devis (numero_devis, idmaison, idfinition, iduser, montant, finition, duree, date_devis, date_debut_travaux, etat_devis) 
VALUES 
('DEV001', 1, 2, 2, 10000.00, 5, 320, '2024-05-01 08:00:00', '2024-10-01 08:00:00', 0);