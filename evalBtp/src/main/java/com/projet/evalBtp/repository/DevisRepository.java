package com.projet.evalBtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.Devis;

@Repository
public interface DevisRepository extends JpaRepository<Devis, Integer> {
    
}
