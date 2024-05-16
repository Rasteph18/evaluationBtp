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
import com.projet.evalBtp.models.CsvDevis;
import com.projet.evalBtp.models.CsvMaisonTravaux;
import com.projet.evalBtp.utils.Erreur;
import com.projet.evalBtp.utils.Util;

@Service
public class ImportDonneeService {
    
    @Autowired
    private CsvDevisService csvDevisService;

    @Autowired
    private CsvMaisonTravauxService csvMaisonTravauxService;

    @Transactional
    public List<Erreur> insertMaisonTravauDevis(MultipartFile fileMaisonTravaux, MultipartFile fileDevis) {
        boolean error = false;
        List<Erreur> listErreur = new ArrayList<>();
        List<CsvMaisonTravaux> listeCsvMaisonTravaux = new ArrayList<>();
        CsvMaisonTravaux csvMaisonTravaux = new CsvMaisonTravaux();
        Class<?> cls = csvMaisonTravaux.getClass();
        Field[] fields = cls.getDeclaredFields();
        Util util = new Util();
        int nbLigne = 1;
    
        try {
            CSVReader reader = new CSVReaderBuilder(new InputStreamReader(fileMaisonTravaux.getInputStream(), "UTF-8"))
                                    .withCSVParser(new CSVParserBuilder().withSeparator(',').build())
                                    .build();
    
            reader.readNext();
            
            String[] data;
            while ((data = reader.readNext()) != null) {
                error = false;

                csvMaisonTravaux = new CsvMaisonTravaux();
                StringBuilder sb = new StringBuilder();
                Erreur erreur = new Erreur();
    
                // début de l'itération en 1 pour éviter le field 'id'
                for (int i = 1; i < fields.length; i++) {
                    String setterName = "set" + util.capitalize(fields[i].getName());
                    try {
                        Method setter = cls.getMethod(setterName, String.class);
                        setter.invoke(csvMaisonTravaux, data[i - 1]);
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
                    erreur.setNomFichier(fileMaisonTravaux.getOriginalFilename());
                    erreur.setMessageErreur(sb.toString());
                    listErreur.add(erreur);
                }
                // System.out.println(csvDevis.toString());
                listeCsvMaisonTravaux.add(csvMaisonTravaux);
                nbLigne++;
            }
    
            // if (!listErreur.isEmpty()) {
            //     throw new Exception("Une erreur s'est produite lors de l'importation des données");
            // }
    
            // upload csv
            // util.uploadCsv(file);
        
        } catch (Exception e) {
            e.printStackTrace();
        }



        boolean error2 = false;

        List<CsvDevis> listeCsvDevis = new ArrayList<>();
        CsvDevis csvDevis = new CsvDevis();
        cls = csvDevis.getClass();
        fields = cls.getDeclaredFields();
        nbLigne = 1;
    
        try {
            CSVReader reader = new CSVReaderBuilder(new InputStreamReader(fileDevis.getInputStream(), "UTF-8"))
                                    .withCSVParser(new CSVParserBuilder().withSeparator(',').build())
                                    .build();
    
            reader.readNext();
            
            String[] data;
            while ((data = reader.readNext()) != null) {
                error2 = false;

                csvDevis = new CsvDevis();
                StringBuilder sb = new StringBuilder();
                Erreur erreur = new Erreur();
    
                // début de l'itération en 1 pour éviter le field 'id'
                for (int i = 1; i < fields.length; i++) {
                    String setterName = "set" + util.capitalize(fields[i].getName());
                    try {
                        Method setter = cls.getMethod(setterName, String.class);
                        setter.invoke(csvDevis, data[i - 1]);
                    } catch (Exception e) {
                        error2 = true;
                        if (e.getMessage() != null) {
                            sb.append(e.getMessage() + " | ");
                        } else {
                            sb.append(e.getCause().getMessage() +  " | ");
                        }
                    }
                }
                if (error2 == true) {
                    erreur.setLigne(nbLigne);
                    erreur.setNomFichier(fileDevis.getOriginalFilename());
                    erreur.setMessageErreur(sb.toString());
                    listErreur.add(erreur);
                }
                // System.out.println(csvDevis.toString());
                listeCsvDevis.add(csvDevis);
                nbLigne++;
            }
    
            // if (!listErreur.isEmpty()) {
            //     throw new Exception("Une erreur s'est produite lors de l'importation des données");
            // }
    
            // upload csv
            // util.uploadCsv(file);
    
            if (listErreur.size() == 0) {
                csvDevisService.insertListeCsvDevis(listeCsvDevis);
                csvMaisonTravauxService.insertAllCsvMaisonTravaux(listeCsvMaisonTravaux);
            }
            
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return listErreur;
    }

    
}
