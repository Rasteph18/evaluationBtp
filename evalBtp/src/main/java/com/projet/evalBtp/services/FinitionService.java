package com.projet.evalBtp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projet.evalBtp.models.Finition;
import com.projet.evalBtp.repository.FinitionRepository;

@Service
public class FinitionService {
    
    @Autowired
    private FinitionRepository finitionRepository;

    public List<Finition> getAllFinition()
    {
        return finitionRepository.findAll(); 
    }

    public Page<Finition> getAllFinition(Pageable pageable)
    {
        return finitionRepository.findAll(pageable); 
    }

    public Finition getByIdFinition(int idFinition)
    {
        return finitionRepository.findById(idFinition);
    }

    public void saveFinition(Finition finition)
    {
        finitionRepository.save(finition);
    }
}
