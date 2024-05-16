package com.projet.evalBtp.utils;

public class Erreur {
    
    private int ligne;
    private String messageErreur;
    private String nomFichier;


    public Erreur() 
    {

    }

    
    public Erreur(int ligne, String messageErreur, String nomFichier) 
    {
        setLigne(ligne);
        setMessageErreur(messageErreur);
        setNomFichier(nomFichier);
    }


    public int getLigne() {
        return ligne;
    }
    public void setLigne(int ligne) {
        this.ligne = ligne;
    }
    public String getMessageErreur() {
        return messageErreur;
    }
    public void setMessageErreur(String messageErreur) {
        this.messageErreur = messageErreur;
    }
    public String getNomFichier() {
        return nomFichier;
    }
    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }
}
