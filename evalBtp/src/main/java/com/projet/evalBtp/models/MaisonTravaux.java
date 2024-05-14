package com.projet.evalBtp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MaisonTravaux {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    private int idMaison;
    private int idTravaux;
    private double quantite;

    
    public MaisonTravaux() 
    {

    }

    


    public MaisonTravaux(int id, int idMaison, int idTravaux, double quantite) {
        setId(id);
        setIdMaison(idMaison);
        setIdTravaux(idTravaux);
        setQuantite(quantite);
    }




    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdMaison() {
        return idMaison;
    }
    public void setIdMaison(int idMaison) {
        this.idMaison = idMaison;
    }
    public int getIdTravaux() {
        return idTravaux;
    }
    public void setIdTravaux(int idTravaux) {
        this.idTravaux = idTravaux;
    }
    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
}
