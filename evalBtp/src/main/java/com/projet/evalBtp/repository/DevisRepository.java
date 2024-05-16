package com.projet.evalBtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.Devis;

@Repository
public interface DevisRepository extends JpaRepository<Devis, Integer> {
    
    @Modifying
    @Query(
        value = """
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
            WHERE d.id IS NULL
                """,
        nativeQuery = true
    )
    public void importDevis();
}
