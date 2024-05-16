package com.projet.evalBtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;

import com.projet.evalBtp.models.Unite;

@Controller
public interface UniteRepository extends JpaRepository<Unite, Integer> {
    
    @Modifying
    @Query(
        value = """
            INSERT INTO unite (nom)
            SELECT
            DISTINCT cmt.unite
            FROM csv_maison_travaux cmt
            LEFT JOIN unite u ON u.nom = cmt.unite
            WHERE u.id IS NULL
                """,
        nativeQuery = true
    )
    public void importUnite();
}
