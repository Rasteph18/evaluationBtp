package com.projet.evalBtp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "V_prix_maison")
public class VPrixMaison {
    
    @Id
    private int id;
    private String nom;
    private String description;
    private double dureeConstruction;
    private int etat;
    private double prix;



    public VPrixMaison() 
    {

    }


    


    public VPrixMaison(int id, String nom, String description, double dureeConstruction, int etat, double prix) {
        setId(id);
        setNom(nom);
        setDescription(description);
        setDureeConstruction(dureeConstruction);
        setEtat(etat);
        setPrix(prix);
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getDureeConstruction() {
        return dureeConstruction;
    }
    public void setDureeConstruction(double dureeConstruction) {
        this.dureeConstruction = dureeConstruction;
    }
    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
}
