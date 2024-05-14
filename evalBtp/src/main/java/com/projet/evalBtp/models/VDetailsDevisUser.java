package com.projet.evalBtp.models;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "V_details_devis_user")
public class VDetailsDevisUser {
    
    @Id
    private int id;
    private int idUser;
    private String typeMaison;
    private String typeFinition;
    private double montantTotal;
    private double duree;
    private Date dateDebutTravaux;
    private double restePayer;

    @Transient
    private Date dateFinTravaux;


    public VDetailsDevisUser() 
    {

    }



    public VDetailsDevisUser(int id, int idUser, String typeMaison, String typeFinition, double montantTotal, double duree,
            Date dateDebutTravaux, double restePayer) {
        setId(id);
        setIdUser(idUser);
        setTypeMaison(typeMaison);
        setTypeFinition(typeFinition);
        setMontantTotal(montantTotal);
        setDuree(duree);
        setDateDebutTravaux(dateDebutTravaux);
        setRestePayer(restePayer);
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
    public double getRestePayer() {
        return restePayer;
    }
    public void setRestePayer(double restePayer) {
        this.restePayer = restePayer;
    }


    public Date getDateFinTravaux() {
        LocalDate dateFin = getDateDebutTravaux().toLocalDate().plusDays((long)getDuree());
        return Date.valueOf(dateFin);
    }

    public void setDateFinTravaux(Date dateFinTravaux) {
        this.dateFinTravaux = dateFinTravaux;
    }
}
