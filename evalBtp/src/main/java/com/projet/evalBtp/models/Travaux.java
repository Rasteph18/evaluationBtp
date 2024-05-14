package com.projet.evalBtp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Travaux {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    private int idTypeTravaux;
    private String code;
    private String nomTravaux;
    private int idUnite;
    private double prixUnitaire;
    private int etat;


    public Travaux() 
    {

    }

    


    public Travaux(int id, int idTypeTravaux, String code, String nomTravaux, int idUnite, double prixUnitaire,
            int etat) {
        setId(id);
        setIdTypeTravaux(idTypeTravaux);
        setCode(code);
        setNomTravaux(nomTravaux);
        setIdUnite(idUnite);
        setPrixUnitaire(prixUnitaire);
        setEtat(etat);
    }




    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdTypeTravaux() {
        return idTypeTravaux;
    }
    public void setIdTypeTravaux(int idTypeTravaux) {
        this.idTypeTravaux = idTypeTravaux;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getNomTravaux() {
        return nomTravaux;
    }
    public void setNomTravaux(String nomTravaux) {
        this.nomTravaux = nomTravaux;
    }
    public int getIdUnite() {
        return idUnite;
    }
    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }
    public double getPrixUnitaire() {
        return prixUnitaire;
    }
    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }
}
