package com.projet.evalBtp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.evalBtp.models.Travaux;
import com.projet.evalBtp.repository.TravauxRepository;

@Service
public class TravauxService {
    
    @Autowired
    private TravauxRepository travauxRepository;

    public Travaux getByIdTravaux(int idTravaux)
    {
        return travauxRepository.findById(idTravaux).get();
    }
}
