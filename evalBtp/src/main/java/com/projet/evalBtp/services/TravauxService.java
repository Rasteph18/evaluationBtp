package com.projet.evalBtp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Travaux> getAllTravaux(Pageable pageable)
    {
        return travauxRepository.findAll(pageable);
    }

    public void saveTravaux(Travaux travaux)
    {
        travauxRepository.save(travaux);
    }
}
