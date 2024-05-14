CREATE DATABASE evalbtp;

\c evalbtp

CREATE TABLE utilisateur(
    id SERIAL PRIMARY KEY,
    mail VARCHAR(255),
    numero VARCHAR(255),
    password VARCHAR(255),
    role INT NOT NULL, -- 10 admin, 20 client
    etat INT NOT NULL -- 0 supprimer, 10 mbola ao
);

CREATE TABLE finition(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    marge DOUBLE PRECISION NOT NULL,
    etat INT NOT NULL
);

CREATE TABLE unite(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    etat INT NOT NULL
);

CREATE TABLE type_travaux(
    id SERIAL PRIMARY KEY,
    code VARCHAR(255) NOT NULL,
    nom VARCHAR(255) NOT NULL,
    etat INT NOT NULL
);

CREATE TABLE travaux(
    id SERIAL PRIMARY KEY,
    id_type_travaux INT,
    code VARCHAR(255) NOT NULL,
    nom_travaux VARCHAR(255) NOT NULL,
    id_unite INT,
    prix_unitaire DOUBLE PRECISION,
    etat INT NOT NULL,

    FOREIGN KEY(id_type_travaux) REFERENCES type_travaux(id),
    FOREIGN KEY(id_unite) REFERENCES unite(id)
);

CREATE TABLE maison(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    duree_construction DOUBLE PRECISION NOT NULL,
    etat INT NOT NULL
);

CREATE TABLE maison_travaux(
    id SERIAL PRIMARY KEY,
    id_maison INT,
    id_travaux INT,
    quantite DOUBLE PRECISION,

    FOREIGN KEY(id_maison) REFERENCES maison(id),
    FOREIGN KEY(id_travaux) REFERENCES travaux(id)
);

CREATE TABLE devis(
    id SERIAL PRIMARY KEY,
    numero VARCHAR(255),
    id_maison INT,
    id_finition INT,
    id_user INT,
    montant DOUBLE PRECISION NOT NULL,
    pourcentage_finition DOUBLE PRECISION NOT NULL,
    duree DOUBLE PRECISION NOT NULL,
    date_devis TIMESTAMP NOT NULL,
    date_debut_travaux DATE,
    etat INT NOT NULL,

    FOREIGN KEY(id_maison) REFERENCES maison(id),
    FOREIGN KEY(id_finition) REFERENCES finition(id),
    FOREIGN KEY(id_user) REFERENCES utilisateur(id)
);

CREATE TABLE payement_devis(
    id SERIAL PRIMARY KEY,
    id_devis INT,
    montant DOUBLE PRECISION NOT NULL,
    date_payement TIMESTAMP NOT NULL,
    etat INT NOT NULL,

    FOREIGN KEY(id_devis) REFERENCES devis(id)
);

CREATE TABLE devis_travaux(
    id SERIAL PRIMARY KEY,
    id_devis INT,
    id_travaux INT,
    prix_unitaire DOUBLE PRECISION NOT NULL,
    quantite DOUBLE PRECISION NOT NULL,

    FOREIGN KEY(id_devis) REFERENCES devis(id),
    FOREIGN KEY(id_travaux) REFERENCES travaux(id)
);

