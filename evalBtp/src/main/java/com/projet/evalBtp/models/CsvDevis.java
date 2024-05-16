package com.projet.evalBtp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class CsvDevis {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    private String client;
    private String refDevis;
    private String typeMaison;
    private String finition;
    private String tauxFinition;
    private String dateDevis;
    private String dateDebut;
    private String lieu;


    public CsvDevis() 
    {

    }



    public CsvDevis(int id, String client, String refDevis, String typeMaison, String finition, String tauxFinition,
            String dateDevis, String dateDebut, String lieu) throws Exception {
        setId(id);
        setClient(client);
        setRefDevis(refDevis);
        setTypeMaison(typeMaison);
        setFinition(finition);
        setTauxFinition(tauxFinition);
        setDateDevis(dateDevis);
        setDateDebut(dateDebut);
        setLieu(lieu);
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getClient() {
        return client;
    }
    public void setClient(String client) {
        this.client = client.trim();
    }
    public String getRefDevis() {
        return refDevis;
    }
    public void setRefDevis(String refDevis) {
        this.refDevis = refDevis.trim();
    }
    public String getTypeMaison() {
        return typeMaison;
    }
    public void setTypeMaison(String typeMaison) {
        this.typeMaison = typeMaison.trim();
    }
    public String getFinition() {
        return finition;
    }
    public void setFinition(String finition) {
        this.finition = finition.trim();
    }
    public String getTauxFinition() {
        return tauxFinition;
    }
    public void setTauxFinition(String tauxFinition) throws Exception {
        tauxFinition = tauxFinition.replace(",", ".").replace("%", "");
        try {
            Double.parseDouble(tauxFinition);
        } catch (Exception e) {
            throw new Exception(tauxFinition + " n'est pas un nombre");
        }
        if (Double.parseDouble(tauxFinition) < 0) {
            throw new Exception("Taux Finition doit etre positif");
        }
        this.tauxFinition = tauxFinition.trim();
    }
    public String getDateDevis() {
        return dateDevis;
    }
    public void setDateDevis(String dateDevis) throws Exception {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(dateDevis, formatter);
            this.dateDevis = dateDevis.trim();
        } catch (Exception e) {
            throw new Exception(dateDevis + " n'est pas convertible en type date");
        }
    }
    public String getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(String dateDebut) throws Exception {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(dateDebut, formatter);
            this.dateDebut = dateDebut.trim();
        } catch (Exception e) {
            throw new Exception(dateDebut + " n'est pas convertible en type date");
        }
    }
    public String getLieu() {
        return lieu;
    }
    public void setLieu(String lieu) {
        this.lieu = lieu.trim();
    }

}
