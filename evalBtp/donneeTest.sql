-- donnees test

INSERT INTO utilisateur (mail, password, role, etat) VALUES ('admin@gmail.com', 'admin', 10, 0);


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


-- INSERT INTO maison (nom, description, duree_construction, surface)
-- SELECT type_maison, description, CAST(duree_travaux AS DOUBLE PRECISION), CAST(surface AS DOUBLE PRECISION) FROM csv_maison_travaux
-- GROUP BY  type_maison, description, CAST(duree_travaux AS DOUBLE PRECISION), CAST(surface AS DOUBLE PRECISION);

-- INSERT INTO unite (nom) 
-- SELECT unite FROM csv_maison_travaux
-- GROUP BY unite;

-- INSERT INTO travaux (code, nom_travaux, id_unite, prix_unitaire)
-- SELECT code_travaux, type_travaux, u.id , CAST(prix_unitaire AS DOUBLE PRECISION) FROM csv_maison_travaux cmt
-- JOIN unite u ON cmt.unite = u.nom
-- GROUP BY code_travaux, type_travaux, u.id , CAST(prix_unitaire AS DOUBLE PRECISION);

-- INSERT INTO maison_travaux (id_maison, id_travaux, quantite)
-- SELECT m.id, t.id, CAST(quantite AS DOUBLE PRECISION)
-- FROM csv_maison_travaux cmt
-- JOIN maison m ON cmt.type_maison = m.nom
-- JOIN travaux t ON cmt.type_travaux = t.nom_travaux
-- GROUP BY id_maison, id_travaux, quantite;

-- INSERT INTO finition (nom, marge)
-- SELECT finition, CAST(taux_finition AS DOUBLE PRECISION) FROM csv_devis
-- GROUP BY finition, CAST(taux_finition AS DOUBLE PRECISION);


-- INSERT INTO devis (numero, id_maison, id_finition, id_user, montant, pourcentage_finition, duree, date_devis, date_debut_travaux, lieu)
-- SELECT ref_devis, m.id, f.id, u.id, CAST(taux_finition AS DOUBLE PRECISION), CAST(m.duree_construction AS DOUBLE PRECISION), to_timestamp(date_devis, 'YYYY-MM-DD hh24:mi:ss')::timestamp, CAST(date_debut_travaux AS DATE), lieu FROM csv_devis cd
-- JOIN maison m ON cd.type_maison = m.nom
-- JOIN finition f ON cd.finition = f.nom
-- JOIN utilisateur u ON cd.client = u.numero




-- INSERT INTO payement_devis (id_devis, referencement, montant, date_payement)
-- SELECT d.id, ref_paiement, CAST(cp.montant AS DOUBLE PRECISION), to_timestamp(date_paiement, 'YYYY-MM-DD hh24:mi:ss')::timestamp
-- FROM csv_paiement cp
-- JOIN devis d ON cp.ref_devis = d.numero;



INSERT INTO maison (nom, description, duree_construction, surface)
SELECT
DISTINCT cmt.type_maison, cmt.description,
CAST(cmt.duree_travaux AS DOUBLE PRECISION),
CAST(cmt.surface AS DOUBLE PRECISION)
FROM csv_maison_travaux cmt
LEFT JOIN maison m ON m.nom = cmt.type_maison
WHERE m.id IS NULL;



INSERT INTO unite (nom)
SELECT
DISTINCT cmt.unite
FROM csv_maison_travaux cmt
LEFT JOIN unite u ON u.nom = cmt.unite
WHERE u.id IS NULL;



INSERT INTO travaux (code, nom_travaux, id_unite, prix_unitaire)
SELECT
DISTINCT code_travaux,
type_travaux,
u.id,
CAST(cmt.prix_unitaire AS DOUBLE PRECISION)
FROM csv_maison_travaux cmt
JOIN unite u ON u.nom = cmt.unite;


INSERT INTO maison_travaux (id_maison, id_travaux, quantite)
SELECT
m.id, t.id, CAST(quantite AS DOUBLE PRECISION)
FROM csv_maison_travaux cmt
JOIN maison m ON m.nom = cmt.type_maison
JOIN travaux t ON t.nom_travaux = cmt.type_travaux;


INSERT INTO utilisateur(numero)
SELECT
DISTINCT client
FROM csv_devis cd
LEFT JOIN utilisateur u ON u.numero = cd.client
WHERE u.id IS NULL;


INSERT INTO finition (nom, marge)
SELECT 
DISTINCT finition,
CAST(taux_finition AS DOUBLE PRECISION)
FROM csv_devis cd
LEFT JOIN finition f ON f.nom = finition
WHERE f.id IS NULL;





INSERT INTO devis (numero, id_maison, id_finition, id_user, montant, pourcentage_finition, duree, date_devis, date_debut_travaux, lieu)
SELECT 
    ref_devis,
    m.id,
    f.id,
    u.id,
    vpm.prix + (vpm.prix*f.marge/100),
    f.marge,
    vpm.duree_construction,
    to_timestamp(cd.date_devis, 'DD/MM/YYYY'),
    to_timestamp(cd.date_debut, 'DD/MM/YYYY'),
    cd.lieu
FROM csv_devis cd
JOIN maison m ON m.nom = cd.type_maison
JOIN finition f ON f.nom = cd.finition
JOIN utilisateur u ON u.numero = cd.client
JOIN V_prix_maison vpm ON vpm.id = m.id
LEFT JOIN devis d ON d.numero = cd.ref_devis
WHERE d.id IS NULL;





INSERT INTO devis_travaux (id_devis, id_travaux, prix_unitaire, quantite)
SELECT 
    d.id,
    t.id,
    t.prix_unitaire,
    mt.quantite
FROM csv_devis cd
JOIN devis d ON d.numero = cd.ref_devis
JOIN maison_travaux mt ON mt.id_maison = d.id_maison
JOIN travaux t ON t.id = mt.id_travaux;



INSERT INTO payement_devis (referencement, id_devis, montant, date_payement)
SELECT 
    cp.ref_paiement,
    d.id,
    CAST(cp.montant AS DOUBLE PRECISION),
    to_timestamp(date_paiement, 'DD/MM/YYYY')
FROM csv_paiement cp
JOIN devis d ON d.numero = cp.ref_devis
LEFT JOIN payement_devis pd ON pd.referencement = cp.ref_paiement
WHERE pd.id IS NULL;