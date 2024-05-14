package com.projet.evalBtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.Maison;

@Repository
public interface MaisonRepository extends JpaRepository<Maison, Integer> {
    
}
