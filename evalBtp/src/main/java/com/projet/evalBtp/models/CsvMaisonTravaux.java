package com.projet.evalBtp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CsvMaisonTravaux {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    private String typeMaison;
    private String description;
    private String surface;
    private String codeTravaux;
    private String typeTravaux;
    private String unite;
    private String prixUnitaire;
    private String quantite;
    private String dureeTravaux;


    public CsvMaisonTravaux() 
    {

    }



    public CsvMaisonTravaux(int id, String typeMaison, String description, String surface, String codeTravaux,
            String typeTravaux, String unite, String prixUnitaire, String quantite, String dureeTravaux) throws Exception {
        setId(id);
        setTypeMaison(typeMaison);
        setDescription(description);
        setSurface(surface);
        setCodeTravaux(codeTravaux);
        setTypeTravaux(typeTravaux);
        setUnite(unite);
        setPrixUnitaire(prixUnitaire);
        setQuantite(quantite);
        setDureeTravaux(dureeTravaux);
    }




    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTypeMaison() {
        return typeMaison;
    }
    public void setTypeMaison(String typeMaison) {
        this.typeMaison = typeMaison.trim();
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description.trim();
    }
    public String getSurface() {
        return surface;
    }
    public void setSurface(String surface) throws Exception {
        surface = surface.replace(",", ".");
        try {
            Double.parseDouble(surface);
        } catch (Exception e) {
            throw new Exception(surface + " ne peut pas etre converti en nombre");
        }

        if (Double.parseDouble(surface) < 0) {
            throw new Exception("Surface doit etre positif");
        }
        this.surface = surface.trim();

    }
    public String getCodeTravaux() {
        return codeTravaux;
    }
    public void setCodeTravaux(String codeTravaux) {
        this.codeTravaux = codeTravaux.trim();
    }
    public String getTypeTravaux() {
        return typeTravaux;
    }
    public void setTypeTravaux(String typeTravaux) {
        this.typeTravaux = typeTravaux.trim();
    }
    public String getUnite() {
        return unite;
    }
    public void setUnite(String unite) {
        this.unite = unite.trim();
    }
    public String getPrixUnitaire() {
        return prixUnitaire;
    }
    public void setPrixUnitaire(String prixUnitaire) throws Exception {
        prixUnitaire = prixUnitaire.replace(",", ".");
        try {
            Double.parseDouble(prixUnitaire);
        } catch (Exception e) {
            throw new Exception(prixUnitaire + " ne peut pas etre converti en nombre");
        }

        if (Double.parseDouble(prixUnitaire) < 0) {
            throw new Exception("Prix unitaire doit etre positif");
        }
        this.prixUnitaire = prixUnitaire.trim();
    }
    public String getQuantite() {
        return quantite;
    }
    public void setQuantite(String quantite) throws Exception {
        quantite = quantite.replace(",", ".");
        try {
            Double.parseDouble(quantite);
        } catch (Exception e) {
            throw new Exception(quantite + " ne peut pas etre converti en nombre");
        }

        if (Double.parseDouble(quantite) < 0) {
            throw new Exception("Quantite doit etre positif");
        }
        this.quantite = quantite.trim();
    }
    public String getDureeTravaux() {
        return dureeTravaux;
    }
    public void setDureeTravaux(String dureeTravaux) throws Exception {
        dureeTravaux = dureeTravaux.replace(",", ".");
        try {
            Double.parseDouble(dureeTravaux);
        } catch (Exception e) {
            throw new Exception(dureeTravaux + " ne peut pas etre converti en nombre");
        }

        if (Double.parseDouble(dureeTravaux) < 0) {
            throw new Exception("Duree travaux doit etre positif");
        }
        this.dureeTravaux = dureeTravaux.trim();
    }


}
