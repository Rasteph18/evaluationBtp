package com.projet.evalBtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.VDevisEnCours;

@Repository
public interface VDevisEnCoursRepository extends JpaRepository<VDevisEnCours, Integer> {
    
}
