package com.projet.evalBtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.DevisTravaux;

@Repository
public interface DevisTravauxRepository extends JpaRepository<DevisTravaux, Integer> {
    
    @Modifying
    @Query(
        value = """
            INSERT INTO devis_travaux (id_devis, id_travaux, prix_unitaire, quantite)
            SELECT 
                d.id,
                t.id,
                t.prix_unitaire,
                mt.quantite
            FROM csv_devis cd
            JOIN devis d ON d.numero = cd.ref_devis
            JOIN maison_travaux mt ON mt.id_maison = d.id_maison
            JOIN travaux t ON t.id = mt.id_travaux
                """,
        nativeQuery = true
    )
    public void importDevisTravaux();
}
