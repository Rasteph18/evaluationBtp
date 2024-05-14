CREATE OR REPLACE VIEW V_prix_maison AS 
SELECT m.id, m.nom, m.description, m.duree_construction, m.etat, SUM(t.prix_unitaire * mt.quantite) AS prix FROM maison m
JOIN maison_travaux AS mt ON m.id = mt.id_maison
JOIN travaux AS t ON mt.id_travaux = t.id
GROUP BY m.id, m.nom, m.description, m.duree_construction, m.etat
ORDER BY m.id;




CREATE OR REPLACE VIEW V_details_devis_user AS
SELECT 
    d.id, 
    d.id_user, 
    m.nom AS type_maison, 
    f.nom AS type_finition, 
    d.montant AS montant_total, 
    d.duree,
    d.date_debut_travaux,  
    (d.montant - COALESCE(SUM(pd.montant), 0)) AS reste_payer
FROM 
    devis d
LEFT JOIN 
    maison m ON d.id_maison = m.id
LEFT JOIN 
    finition f ON d.id_finition = f.id
LEFT JOIN 
    payement_devis pd ON d.id = pd.id_devis
GROUP BY 
    d.id, 
    d.id_user, 
    m.nom, 
    f.nom, 
    d.montant, 
    d.duree,
    d.date_debut_travaux;


CREATE OR REPLACE VIEW V_pdf_devis AS
SELECT dt.id, dt.id_devis, t.code, t.nom_travaux, u.nom AS unite, dt.prix_unitaire, dt.quantite
FROM devis_travaux dt
JOIN travaux t ON dt.id_travaux = t.id
JOIN unite u ON t.id_unite = u.id;


CREATE OR REPLACE VIEW V_devis_en_cours AS
SELECT 
    d.id, 
    d.id_user, 
    m.nom AS type_maison, 
    f.nom AS type_finition, 
    d.montant AS montant_total, 
    d.duree,
    d.date_debut_travaux,  
    COALESCE(SUM(pd.montant), 0) AS deja_payer,
    (COALESCE(SUM(pd.montant), 0) * 100) / d.montant AS pourcentage_effectue
FROM 
    devis d
LEFT JOIN 
    maison m ON d.id_maison = m.id
LEFT JOIN 
    finition f ON d.id_finition = f.id
LEFT JOIN 
    payement_devis pd ON d.id = pd.id_devis
GROUP BY 
    d.id, 
    d.id_user, 
    m.nom, 
    f.nom, 
    d.montant, 
    d.duree,
    d.date_debut_travaux;
