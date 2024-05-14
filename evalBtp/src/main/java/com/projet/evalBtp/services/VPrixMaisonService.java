package com.projet.evalBtp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.evalBtp.models.VPrixMaison;
import com.projet.evalBtp.repository.VPrixMaisonRepository;

@Service
public class VPrixMaisonService {
    
    @Autowired
    private VPrixMaisonRepository vPrixMaisonRepository;

    public List<VPrixMaison> getAllPrixMaison()
    {
        return vPrixMaisonRepository.findAll();
    }

    public VPrixMaison getByIdMaison(int idMaison)
    {
        return vPrixMaisonRepository.findById(idMaison);
    }
}
