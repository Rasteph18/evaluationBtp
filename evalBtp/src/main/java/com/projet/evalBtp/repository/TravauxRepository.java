package com.projet.evalBtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.Travaux;

@Repository
public interface TravauxRepository extends JpaRepository<Travaux, Integer> {
    
    @Modifying
    @Query(
        value = """
            INSERT INTO travaux (code, nom_travaux, id_unite, prix_unitaire)
            SELECT
            DISTINCT code_travaux,
            type_travaux,
            u.id,
            CAST(cmt.prix_unitaire AS DOUBLE PRECISION)
            FROM csv_maison_travaux cmt
            JOIN unite u ON u.nom = cmt.unite
                """,
        nativeQuery = true
    )
    public void importTravaux();
}
