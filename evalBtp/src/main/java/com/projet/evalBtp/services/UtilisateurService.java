package com.projet.evalBtp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.evalBtp.models.Utilisateur;
import com.projet.evalBtp.repository.UtilisateurRepository;

@Service
public class UtilisateurService {
    
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur getUtilisateurByNumero(String numero) throws Exception
    {
        Utilisateur user = utilisateurRepository.findByNumero(numero);
        if (user == null) {
            user = insertNouveauUtilisateur(numero);
        }

        return user;
    }

    public Utilisateur insertNouveauUtilisateur(String numero) throws Exception
    {
        Utilisateur user = new Utilisateur();
        user.setNumero(numero);
        user.setRole(20);
        user.setEtat(10);

        return utilisateurRepository.save(user);
    }

    public Utilisateur getUtilisateurByMailEtMdp(String mail, String mdp)
    {
        return utilisateurRepository.findByMailAndPassword(mail, mdp);
    }
}
