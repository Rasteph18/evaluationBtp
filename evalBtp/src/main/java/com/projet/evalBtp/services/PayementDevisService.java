package com.projet.evalBtp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projet.evalBtp.models.PayementDevis;
import com.projet.evalBtp.repository.PayementDevisRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class PayementDevisService {
    
    @Autowired
    private PayementDevisRepository payementDevisRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public void insertPayement(String idDevis, String montant, String datePayement) throws Exception
    {
        PayementDevis payementDevis = new PayementDevis();
        payementDevis.setIdDevis(idDevis);
        payementDevis.setMontant(montant);
        payementDevis.setDatePayement(datePayement);
        payementDevis.setEtat(0);

        payementDevisRepository.save(payementDevis);
    }

    public double getSommePayerByIdDevis(int idDevis)
    {
        String requete = "SELECT COALESCE(SUM(montant), 0) FROM payement_devis WHERE id_devis = :idDevis";
        Query query = entityManager.createNativeQuery(requete);
        query.setParameter("idDevis", idDevis);

        double somme = Double.parseDouble(query.getSingleResult().toString());

        return somme;
    }

    public double montantTotalPaiementEffectue()
    {
        String requete = "SELECT COALESCE(SUM(montant), 0) FROM payement_devis";
        Query query = entityManager.createNativeQuery(requete);

        double somme = Double.parseDouble(query.getSingleResult().toString());

        return somme;
    }


    @Transactional
    public void importPayementDevis()
    {
        payementDevisRepository.importPayementDevis();
    }


    public List<PayementDevis> getByIdDevis(int idDevis)
    {
        return payementDevisRepository.findByIdDevis(idDevis);
    }
}
