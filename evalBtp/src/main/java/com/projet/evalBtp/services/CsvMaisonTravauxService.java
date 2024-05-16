package com.projet.evalBtp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projet.evalBtp.models.CsvMaisonTravaux;
import com.projet.evalBtp.repository.CsvMaisonTravauxRepository;

@Service
public class CsvMaisonTravauxService {
    
    @Autowired
    private CsvMaisonTravauxRepository csvMaisonTravauxRepository;

    public void insertAllCsvMaisonTravaux(List<CsvMaisonTravaux> listeMaisonTravaux)
    {
        csvMaisonTravauxRepository.saveAll(listeMaisonTravaux);
    }

    @Transactional
    public void viderCsvMaisonTravaux()
    {
        csvMaisonTravauxRepository.viderCsvMaisonTravaux();
    }
}
