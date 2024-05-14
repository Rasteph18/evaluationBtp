package com.projet.evalBtp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.evalBtp.models.MaisonTravaux;
import com.projet.evalBtp.repository.MaisonTravauxRepository;

@Service
public class MaisonTravauxService {
    
    @Autowired
    private MaisonTravauxRepository maisonTravauxRepository;

    public List<MaisonTravaux> getByIdMaison(int idMaison)
    {
        return maisonTravauxRepository.findByIdMaison(idMaison);
    }
}
