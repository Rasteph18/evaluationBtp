package com.projet.evalBtp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DevisTravaux {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    private int idDevis;
    private int idTravaux;
    private double prixUnitaire;
    private double quantite;


    
    public DevisTravaux() 
    {

    }

    

    public DevisTravaux(int id, int idDevis, int idTravaux, double prixUnitaire, double quantite) {
        setId(id);
        setIdDevis(idDevis);
        setIdTravaux(idTravaux);
        setPrixUnitaire(prixUnitaire);
        setQuantite(quantite);
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
    public int getIdTravaux() {
        return idTravaux;
    }
    public void setIdTravaux(int idTravaux) {
        this.idTravaux = idTravaux;
    }
    public double getPrixUnitaire() {
        return prixUnitaire;
    }
    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
}
