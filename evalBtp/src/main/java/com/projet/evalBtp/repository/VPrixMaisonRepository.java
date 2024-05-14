package com.projet.evalBtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.VPrixMaison;

@Repository
public interface VPrixMaisonRepository extends JpaRepository<VPrixMaison, Integer> {
    
    public VPrixMaison findById(int idMaison);
}
