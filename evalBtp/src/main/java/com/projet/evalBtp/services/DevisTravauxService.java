package com.projet.evalBtp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projet.evalBtp.models.DevisTravaux;
import com.projet.evalBtp.repository.DevisTravauxRepository;

@Service
public class DevisTravauxService {
    
    @Autowired
    private DevisTravauxRepository devisTravauxRepository;

    @Transactional
    public void insertDevisTravaux(DevisTravaux devisTravaux)
    {
        devisTravauxRepository.save(devisTravaux); 
    }

    public void insertDevisTravaux(List<DevisTravaux> listeDevisTravaux)
    {
        devisTravauxRepository.saveAll(listeDevisTravaux);
    }
}
