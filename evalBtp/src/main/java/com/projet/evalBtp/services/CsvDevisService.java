package com.projet.evalBtp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projet.evalBtp.models.CsvDevis;
import com.projet.evalBtp.repository.CsvDevisRepository;

@Service
public class CsvDevisService {
    
    @Autowired
    private CsvDevisRepository csvDevisRepository;



    public void insertListeCsvDevis(List<CsvDevis> listeCsvDevis)
    {
        csvDevisRepository.saveAll(listeCsvDevis);
    }

    @Transactional
    public void viderCsvDevis()
    {
        csvDevisRepository.viderCsvDevis();
    }
}
