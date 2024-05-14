package com.projet.evalBtp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.evalBtp.models.Unite;
import com.projet.evalBtp.repository.UniteRepository;

@Service
public class UniteService {
    
    @Autowired
    private UniteRepository uniteRepository;


    public List<Unite> getAllUnite()
    {
        return uniteRepository.findAll();
    }

    public Unite getUniteById(int id)
    {
        return uniteRepository.findById(id).get();
    }
}
