package com.projet.evalBtp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projet.evalBtp.models.VDevisEnCours;
import com.projet.evalBtp.repository.VDevisEnCoursRepository;

@Service
public class VDevisEnCoursService {
    
    @Autowired
    private VDevisEnCoursRepository vDevisEnCoursRepository;

    public Page<VDevisEnCours> getAllDevisEnCours(Pageable pageable)
    {
        return vDevisEnCoursRepository.findAll(pageable);
    }
}
