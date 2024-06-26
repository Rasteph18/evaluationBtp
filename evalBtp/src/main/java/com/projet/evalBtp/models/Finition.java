package com.projet.evalBtp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Finition {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    private String nom;
    private double marge;
    private int etat;



    public Finition() 
    {

    }



    public Finition(int id, String nom, double marge, int etat) throws Exception {
        setId(id);
        setNom(nom);
        setMarge(marge);
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
    public void setNom(String nom) throws Exception {
        if (nom != null && !nom.trim().isEmpty()) {
            this.nom = nom;
        } else {
            throw new Exception("Entrez le nom finition");
        }
    }
    public double getMarge() {
        return marge;
    }
    public void setMarge(double marge) throws Exception {
        if (marge < 0) {
            throw new Exception("Le marge doit etre positif");
        }
        this.marge = marge;
    }
    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }


}
