package com.projet.evalBtp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projet.evalBtp.models.VPdfDevis;
import com.projet.evalBtp.repository.VPdfDevisRepository;

@Service
public class VPdfDevisService {
 
    @Autowired
    private VPdfDevisRepository vPdfDevisRepository;

    public List<VPdfDevis> getAllDetailsDevisByIdDevis(int idDedis)
    {
        return vPdfDevisRepository.findByIdDevis(idDedis);
    }

    public Page<VPdfDevis> getAllDetailsDevisByIdDevis(int idDevis, Pageable pageable)
    {
        return vPdfDevisRepository.findByIdDevis(idDevis, pageable);
    }
}
