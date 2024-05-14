package com.projet.evalBtp.models;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Devis {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    private String numero;
    private int idMaison;
    private int idFinition;
    private int idUser;
    private double montant;
    private double pourcentageFinition;
    private double duree;
    private LocalDateTime dateDevis;
    private Date dateDebutTravaux;
    private int etat;


    public Devis() 
    {

    }



    public Devis(int id, String numero, int idMaison, int idFinition, int idUser, double montant,
            double pourcentageFinition, double duree, LocalDateTime dateDevis, Date dateDebutTravaux, int etat) {
        setId(id);
        setNumero(numero);
        setIdMaison(idMaison);
        setIdFinition(idFinition);
        setIdUser(idUser);
        setMontant(montant);
        setPourcentageFinition(pourcentageFinition);
        setDuree(duree);
        setDateDevis(dateDevis);
        setDateDebutTravaux(dateDebutTravaux);
        setEtat(etat);
    }




    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public int getIdMaison() {
        return idMaison;
    }
    public void setIdMaison(int idMaison) {
        this.idMaison = idMaison;
    }
    public int getIdFinition() {
        return idFinition;
    }
    public void setIdFinition(int idFinition) {
        this.idFinition = idFinition;
    }
    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }
    public double getPourcentageFinition() {
        return pourcentageFinition;
    }
    public void setPourcentageFinition(double pourcentageFinition) {
        this.pourcentageFinition = pourcentageFinition;
    }
    public double getDuree() {
        return duree;
    }
    public void setDuree(double duree) {
        this.duree = duree;
    }
    public LocalDateTime getDateDevis() {
        return dateDevis;
    }
    public void setDateDevis(LocalDateTime dateDevis) {
        this.dateDevis = dateDevis;
    }
    public Date getDateDebutTravaux() {
        return dateDebutTravaux;
    }
    public void setDateDebutTravaux(Date dateDebutTravaux) {
        this.dateDebutTravaux = dateDebutTravaux;
    }
    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }
   
    
}
