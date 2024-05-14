package com.projet.evalBtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.Finition;

@Repository
public interface FinitionRepository extends JpaRepository<Finition, Integer> {
    
    public Finition findById(int idFinition);
}
