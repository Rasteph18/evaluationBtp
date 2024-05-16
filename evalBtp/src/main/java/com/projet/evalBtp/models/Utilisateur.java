package com.projet.evalBtp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class Utilisateur {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    private String mail;
    private String numero;
    private String password;
    private int role;
    private int etat;


    public Utilisateur() 
    {

    }


    public Utilisateur(int id, String mail, String numero, String password, int role, int etat) throws Exception {
        setId(id);
        setMail(mail);
        setNumero(numero);
        setPassword(password);
        setRole(role);
        setEtat(etat);
    }

    public String getRoleAuth()
    {
        if (role == 20) {
            return "CLIENT";
        } else if (role == 10) {
            return "BTP";
        }
        return "USER";
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) throws Exception {
        if (mail != null && !mail.trim().isEmpty()) {
            this.mail = mail;
        } else {
            throw new Exception("Entrez votre mail");
        }
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero)throws Exception {
        if (numero != null && !numero.trim().isEmpty()) {
            // String regex = "(0|261)?(32|33|34|38)[0-9]{7}";
            // Pattern pattern = Pattern.compile(regex);
            // Matcher matcher = pattern.matcher(numero);

            // if (matcher.matches()) {
            //     this.numero = numero;
            // } else {
            //     throw new Exception(numero + " n'est pas un numéro de téléphone malgache valide.");
            // }
            this.numero = numero;
        } else {
            throw new Exception("Entrez votre numero");
        }
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) throws Exception {
        if (password != null && !password.isEmpty()) {
            this.password = password;
        } else {
            throw new Exception("Entrez votre mot de passe");
        }
        this.password = password;
    }
    public int getRole() {
        return role;
    }
    public void setRole(int role) {
        this.role = role;
    }
    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }
}
