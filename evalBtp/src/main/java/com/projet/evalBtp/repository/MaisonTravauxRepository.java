package com.projet.evalBtp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.MaisonTravaux;

@Repository
public interface MaisonTravauxRepository extends JpaRepository<MaisonTravaux, Integer> {
    
    public List<MaisonTravaux> findByIdMaison(int idMaison);


    @Modifying
    @Query(
        value = """
            INSERT INTO maison_travaux (id_maison, id_travaux, quantite)
            SELECT
            m.id, t.id, CAST(quantite AS DOUBLE PRECISION)
            FROM csv_maison_travaux cmt
            JOIN maison m ON m.nom = cmt.type_maison
            JOIN travaux t ON t.nom_travaux = cmt.type_travaux
                """,
            nativeQuery = true
    )
    public void importMaisonTravaux();
}
