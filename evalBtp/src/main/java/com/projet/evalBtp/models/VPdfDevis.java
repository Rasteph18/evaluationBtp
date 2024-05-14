package com.projet.evalBtp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "V_pdf_devis")
public class VPdfDevis {
    
    @Id
    private int id;
    private int idDevis;
    private String code;
    private String nomTravaux;
    private String unite;
    private double prixUnitaire;
    private double quantite;
    

    public VPdfDevis() 
    {

    }


    public VPdfDevis(int id, int idDevis, String code, String nomTravaux, String unite, double prixUnitaire,
            double quantite) {
        setId(id);
        setIdDevis(idDevis);
        setCode(code);
        setNomTravaux(nomTravaux);
        setUnite(unite);
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
    public String getUnite() {
        return unite;
    }
    public void setUnite(String unite) {
        this.unite = unite;
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
