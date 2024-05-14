package com.projet.evalBtp.models;

public class VStatMontantDevisMoisAnnee {
    
    private int mois;
    private int annee;
    private double totalMontant;


    public VStatMontantDevisMoisAnnee()
    {

    }

    


    public VStatMontantDevisMoisAnnee(int mois, int annee, double totalMontant) {
        setMois(mois);
        setAnnee(annee);
        setTotalMontant(totalMontant);
    }


    public int getMois() {
        return mois;
    }
    public void setMois(int mois) {
        this.mois = mois;
    }
    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    public double getTotalMontant() {
        return totalMontant;
    }
    public void setTotalMontant(double totalMontant) {
        this.totalMontant = totalMontant;
    }
}
