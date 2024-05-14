package com.projet.evalBtp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.MaisonTravaux;

@Repository
public interface MaisonTravauxRepository extends JpaRepository<MaisonTravaux, Integer> {
    
    public List<MaisonTravaux> findByIdMaison(int idMaison);
}
