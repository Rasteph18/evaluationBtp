package com.projet.evalBtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.CsvMaisonTravaux;

@Repository
public interface CsvMaisonTravauxRepository extends JpaRepository<CsvMaisonTravaux, Integer> {
    
    @Modifying
    @Query(
        value = "TRUNCATE TABLE csv_maison_travaux CASCADE",
        nativeQuery = true
    )
    public void viderCsvMaisonTravaux();
}
