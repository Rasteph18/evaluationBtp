package com.projet.evalBtp.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PayementDevis {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    private int idDevis;
    private double montant;
    private LocalDateTime datePayement;
    private int etat;


    public PayementDevis() 
    {

    }



    public PayementDevis(int id, int idDevis, double montant, LocalDateTime datePayement, int etat) throws Exception {
        setId(id);
        setIdDevis(idDevis);
        setMontant(montant);
        setDatePayement(datePayement);
        setEtat(etat);
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdDevis() {
        return idDevis;
    }
    public void setIdDevis(int idDevis) {
        this.idDevis = idDevis;
    }
    public void setIdDevis(String idDevis) throws Exception {
        try {
            this.idDevis = Integer.parseInt(idDevis);
        } catch (Exception e) {
            throw new Exception("L'id devis doit etre de type nombre");
        }
    }
    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) throws Exception {
        if (montant < 0) {
            throw new Exception("Le montant doit être positif");
        }
        this.montant = montant;
    }
    public void setMontant(String montant) throws Exception {
        this.montant = Double.parseDouble(montant.trim());
        if (getMontant() < 0) {
            throw new Exception("Le montant doit être positif");
        }
    }
    public LocalDateTime getDatePayement() {
        return datePayement;
    }
    public void setDatePayement(LocalDateTime datePayement) {
        this.datePayement = datePayement;
    }
    public void setDatePayement(String datePayement) throws Exception {
        LocalDateTime localDateTime = LocalDateTime.parse(datePayement, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        if (localDateTime.isBefore(LocalDateTime.now())) {
            throw new Exception("La date doit être supérieur à aujourd'hui");
        }

        this.datePayement = localDateTime;
    }

    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }
}
