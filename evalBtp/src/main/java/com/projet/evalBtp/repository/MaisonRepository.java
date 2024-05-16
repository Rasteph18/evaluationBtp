package com.projet.evalBtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.Maison;

@Repository
public interface MaisonRepository extends JpaRepository<Maison, Integer> {
 
    @Modifying
    @Query(
        value = """
            INSERT INTO maison (nom, description, duree_construction, surface)
            SELECT
            DISTINCT cmt.type_maison, cmt.description,
            CAST(cmt.duree_travaux AS DOUBLE PRECISION),
            CAST(cmt.surface AS DOUBLE PRECISION)
            FROM csv_maison_travaux cmt
            LEFT JOIN maison m ON m.nom = cmt.type_maison
            WHERE m.id IS NULL
                """,
        nativeQuery = true
    )
    public void importMaison();
}
