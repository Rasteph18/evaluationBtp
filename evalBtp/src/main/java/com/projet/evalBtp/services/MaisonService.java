package com.projet.evalBtp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projet.evalBtp.models.Maison;
import com.projet.evalBtp.repository.MaisonRepository;

@Service
public class MaisonService {
    
    @Autowired
    private MaisonRepository maisonRepository;

    public List<Maison> getAllMaison()
    {
        return maisonRepository.findAll();
    }

    @Transactional
    public void importMaison()
    {
        maisonRepository.importMaison();
    }
}
