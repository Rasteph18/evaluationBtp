package com.projet.evalBtp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class CsvPaiement {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    private String refDevis;
    private String refPaiement;
    private String datePaiement;
    private String montant;


    public CsvPaiement() 
    {

    }



    public CsvPaiement(int id, String refDevis, String refPaiement, String datePaiement, String montant) throws Exception {
        setId(id);
        setRefDevis(refDevis);
        setRefPaiement(refPaiement);
        setDatePaiement(datePaiement);
        setMontant(montant);
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getRefDevis() {
        return refDevis;
    }
    public void setRefDevis(String refDevis) {
        this.refDevis = refDevis.trim();
    }
    public String getRefPaiement() {
        return refPaiement;
    }
    public void setRefPaiement(String refPaiement) {
        this.refPaiement = refPaiement.trim();
    }
    public String getDatePaiement() {
        return datePaiement;
    }
    public void setDatePaiement(String datePaiement) throws Exception {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(datePaiement, formatter);
            this.datePaiement = datePaiement.trim();
        } catch (Exception e) {
            throw new Exception(datePaiement + " n'est pas convertible en type date");
        }
        
    }
    public String getMontant() {
        return montant;
    }
    public void setMontant(String montant) throws Exception {
        montant = montant.replace(",", ".");
        try {
            Double.parseDouble(montant);
        } catch (Exception e) {
            throw new Exception(montant + " n'est pas convertible en nombre");
        }

        if (Double.parseDouble(montant) <= 0) {
            throw new Exception("Montant doit etre superieur a 0");
        }
        this.montant = montant.trim();
    }


}
