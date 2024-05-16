package com.projet.evalBtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.Finition;

@Repository
public interface FinitionRepository extends JpaRepository<Finition, Integer> {
    
    public Finition findById(int idFinition);

    @Modifying
    @Query(
        value = """
            INSERT INTO finition (nom, marge)
            SELECT 
            DISTINCT finition,
            CAST(taux_finition AS DOUBLE PRECISION)
            FROM csv_devis cd
            LEFT JOIN finition f ON f.nom = finition
            WHERE f.id IS NULL
                """,
        nativeQuery = true
    )
    public void importFinition();
}
