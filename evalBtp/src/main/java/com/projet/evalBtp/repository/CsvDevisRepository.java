package com.projet.evalBtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.CsvDevis;

@Repository
public interface CsvDevisRepository extends JpaRepository<CsvDevis, Integer> {
    
    @Modifying
    @Query(
        value = "TRUNCATE TABLE csv_devis CASCADE",
        nativeQuery = true
    )
    public void viderCsvDevis();
}
