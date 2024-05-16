package com.projet.evalBtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.CsvPaiement;

@Repository
public interface CsvPaiementRepository extends JpaRepository<CsvPaiement, Integer> {
    

    @Modifying
    @Query(
        value = "TRUNCATE TABLE csv_paiement CASCADE",
        nativeQuery = true
    )
    public void viderCsvPaiement();
}
