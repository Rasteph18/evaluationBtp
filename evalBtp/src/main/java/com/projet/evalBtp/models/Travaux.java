package com.projet.evalBtp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Travaux {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    private Integer idTypeTravaux;
    private String code;
    private String nomTravaux;
    @ManyToOne
    @JoinColumn(name = "id_unite")
    private Unite unite;
    private double prixUnitaire;
    private int etat;


    public Travaux() 
    {

    }

    


    public Travaux(int id, int idTypeTravaux, String code, String nomTravaux, Unite unite, double prixUnitaire,
            int etat) throws Exception {
        setId(id);
        setIdTypeTravaux(idTypeTravaux);
        setCode(code);
        setNomTravaux(nomTravaux);
        setUnite(unite);
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
    public void setIdTypeTravaux(Integer idTypeTravaux) {
        this.idTypeTravaux = idTypeTravaux;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) throws Exception {
        if (code != null && !code.trim().isEmpty()) {
            this.code = code;
        } else {
            throw new Exception("Entrez le code");
        }
    }
    public String getNomTravaux() {
        return nomTravaux;
    }
    public void setNomTravaux(String nomTravaux) throws Exception {
        if (nomTravaux != null && !nomTravaux.trim().isEmpty()) {
            this.nomTravaux = nomTravaux;
        } else {
            throw new Exception("Entrez le nom travaux");
        }
    }
    public Unite getUnite() {
        return unite;
    }
    public void setUnite(Unite unite) throws Exception {
        if (unite != null) {
            this.unite = unite;
        } else {
            throw new Exception("Selectionnez un unité");
        }
    }
    public double getPrixUnitaire() {
        return prixUnitaire;
    }
    public void setPrixUnitaire(double prixUnitaire) throws Exception {
        if (prixUnitaire < 0) {
            throw new Exception("Le prix unitaire doit etre positif");
        }
        this.prixUnitaire = prixUnitaire;
    }
    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }
}
