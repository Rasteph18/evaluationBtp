package com.projet.evalBtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.Travaux;

@Repository
public interface TravauxRepository extends JpaRepository<Travaux, Integer> {
    
}
