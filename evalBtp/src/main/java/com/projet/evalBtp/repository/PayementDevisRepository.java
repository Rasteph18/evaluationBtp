package com.projet.evalBtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.PayementDevis;

@Repository
public interface PayementDevisRepository extends JpaRepository<PayementDevis, Integer> {
    
}
