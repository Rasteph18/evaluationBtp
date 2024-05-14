package com.projet.evalBtp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Maison {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    private String nom;
    private String description;
    private double dureeConstruction;
    private int etat;


    public Maison() 
    {

    }

    


    public Maison(int id, String nom, String description, double dureeConstruction, int etat) {
        setId(id);
        setNom(nom);
        setDescription(description);
        setDureeConstruction(dureeConstruction);
        setEtat(etat);
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
}
