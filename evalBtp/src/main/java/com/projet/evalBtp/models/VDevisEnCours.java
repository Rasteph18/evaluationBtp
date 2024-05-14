package com.projet.evalBtp.models;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "V_devis_en_cours")
public class VDevisEnCours {
    
    @Id
    private int id;
    private int idUser;
    private String typeMaison;
    private String typeFinition;
    private double montantTotal;
    private double duree;
    private Date dateDebutTravaux;
    private double dejaPayer;
    private double pourcentageEffectue;

    
    @Transient
    private Date dateFinTravaux;


    public VDevisEnCours() 
    {

    }

    


    public VDevisEnCours(int id, int idUser, String typeMaison, String typeFinition, double montantTotal, double duree,
            Date dateDebutTravaux, double dejaPayer) {
        setId(id);
        setIdUser(idUser);
        setTypeMaison(typeMaison);
        setTypeFinition(typeFinition);
        setMontantTotal(montantTotal);
        setDuree(duree);
        setDateDebutTravaux(dateDebutTravaux);
        setDejaPayer(dejaPayer);
    }




    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public String getTypeMaison() {
        return typeMaison;
    }
    public void setTypeMaison(String typeMaison) {
        this.typeMaison = typeMaison;
    }
    public String getTypeFinition() {
        return typeFinition;
    }
    public void setTypeFinition(String typeFinition) {
        this.typeFinition = typeFinition;
    }
    public double getMontantTotal() {
        return montantTotal;
    }
    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }
    public double getDuree() {
        return duree;
    }
    public void setDuree(double duree) {
        this.duree = duree;
    }
    public Date getDateDebutTravaux() {
        return dateDebutTravaux;
    }
    public void setDateDebutTravaux(Date dateDebutTravaux) {
        this.dateDebutTravaux = dateDebutTravaux;
    }
    public double getDejaPayer() {
        return dejaPayer;
    }
    public void setDejaPayer(double dejaPayer) {
        this.dejaPayer = dejaPayer;
    }
    public double getPourcentageEffectue() {
        return pourcentageEffectue;
    }
    public void setPourcentageEffectue(double pourcentageEffectue) {
        this.pourcentageEffectue = pourcentageEffectue;
    }

    public Date getDateFinTravaux() {
        LocalDate dateFin = getDateDebutTravaux().toLocalDate().plusDays((long)getDuree());
        return Date.valueOf(dateFin);
    }
    public void setDateFinTravaux(Date dateFinTravaux) {
        this.dateFinTravaux = dateFinTravaux;
    }
    
}
