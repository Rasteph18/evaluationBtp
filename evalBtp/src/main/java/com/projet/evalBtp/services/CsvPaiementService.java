package com.projet.evalBtp.services;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.projet.evalBtp.models.CsvPaiement;
import com.projet.evalBtp.repository.CsvPaiementRepository;
import com.projet.evalBtp.utils.Erreur;
import com.projet.evalBtp.utils.Util;

@Service
public class CsvPaiementService {
    
    @Autowired
    private CsvPaiementRepository csvPaiementRepository;

    public void saveAllCsvPaiement(List<CsvPaiement> listeCsvPaiement)
    {
        csvPaiementRepository.saveAll(listeCsvPaiement);
    }

    @Transactional
    public void viderCsvPaiement()
    {
        csvPaiementRepository.viderCsvPaiement();
    }


    @Transactional
    public List<Erreur> insertPaiementCsv(MultipartFile file) {
        boolean error = false;
        List<Erreur> listErreur = new ArrayList<>();
        List<CsvPaiement> listeCsvPaiement = new ArrayList<>();
        CsvPaiement csvPaiement = new CsvPaiement();
        Class<?> cls = csvPaiement.getClass();
        Field[] fields = cls.getDeclaredFields();
        Util util = new Util();
        int nbLigne = 1;
    
        try {
            CSVReader reader = new CSVReaderBuilder(new InputStreamReader(file.getInputStream(), "UTF-8"))
                                    .withCSVParser(new CSVParserBuilder().withSeparator(',').build())
                                    .build();
    
            reader.readNext();
            
            String[] data;
            while ((data = reader.readNext()) != null) {
                error = false;

                csvPaiement = new CsvPaiement();
                StringBuilder sb = new StringBuilder();
                Erreur erreur = new Erreur();
    
                // début de l'itération en 1 pour éviter le field 'id'
                for (int i = 1; i < fields.length; i++) {
                    String setterName = "set" + util.capitalize(fields[i].getName());
                    try {
                        Method setter = cls.getMethod(setterName, String.class);
                        setter.invoke(csvPaiement, data[i - 1]);
                    } catch (Exception e) {
                        error = true;
                        if (e.getMessage() != null) {
                            sb.append(e.getMessage() + " | ");
                        } else {
                            sb.append(e.getCause().getMessage() +  " | ");
                        }
                    }
                }
                if (error == true) {
                    erreur.setLigne(nbLigne);
                    erreur.setNomFichier(file.getOriginalFilename());
                    erreur.setMessageErreur(sb.toString());
                    listErreur.add(erreur);
                }
                // System.out.println(csvDevis.toString());
                listeCsvPaiement.add(csvPaiement);
                nbLigne++;
            }
    
            // if (!listErreur.isEmpty()) {
            //     throw new Exception("Une erreur s'est produite lors de l'importation des données");
            // }
    
            // upload csv
            // util.uploadCsv(file);

            if (listErreur.size() == 0) {
                saveAllCsvPaiement(listeCsvPaiement);
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return listErreur;
    }


}
