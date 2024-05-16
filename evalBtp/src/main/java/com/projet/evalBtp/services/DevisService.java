package com.projet.evalBtp.services;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projet.evalBtp.models.Devis;
import com.projet.evalBtp.models.DevisTravaux;
import com.projet.evalBtp.models.Finition;
import com.projet.evalBtp.models.MaisonTravaux;
import com.projet.evalBtp.models.Travaux;
import com.projet.evalBtp.models.VPrixMaison;
import com.projet.evalBtp.repository.DevisRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class DevisService {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DevisRepository devisRepository;

    @Autowired
    private VPrixMaisonService vPrixMaisonService;

    @Autowired
    private FinitionService finitionService;

    @Autowired
    private MaisonTravauxService maisonTravauxService;

    @Autowired
    private DevisTravauxService devisTravauxService;

    @Autowired
    private TravauxService travauxService;

    @Transactional
    public Devis insertDevis(int idMaison, int idFinition, int idUser, String dateDebutTravaux, String lieu) throws Exception
    {
        VPrixMaison maison = vPrixMaisonService.getByIdMaison(idMaison);
        Finition finition = finitionService.getByIdFinition(idFinition);
        

        double montantTotal = maison.getPrix() + ((maison.getPrix() * finition.getMarge())/100);

        Devis devis = new Devis();
        devis.setIdMaison(idMaison);
        devis.setIdFinition(idFinition);
        devis.setIdUser(idUser);
        devis.setMontant(montantTotal);
        devis.setPourcentageFinition(finition.getMarge());
        devis.setDuree(maison.getDureeConstruction());
        devis.setDateDevis(LocalDateTime.now());
        if (lieu != null && !lieu.isEmpty()) {
            devis.setLieu(lieu);
        }

        devis.setDateDebutTravaux(Date.valueOf(LocalDate.parse(dateDebutTravaux)));
        if (devis.getDateDebutTravaux().before(Date.valueOf(LocalDate.now()))) {
            throw new Exception("La date de debut du travaux doit etre superieur a aujourd'hui");
        }
        devis.setEtat(0);

        return devisRepository.save(devis);
    }

    @Transactional
    public void insertDevisEtDevisTravaux(int idMaison, int idFinition, int idUser, String dateDebutTravaux, String lieu) throws Exception
    {
        Devis nouveauDevis = insertDevis(idMaison, idFinition, idUser, dateDebutTravaux, lieu);

        List<MaisonTravaux> listeMaisonTravaux = maisonTravauxService.getByIdMaison(idMaison);
        List<DevisTravaux> listeDevisTravaux = new ArrayList<DevisTravaux>();
        
        DevisTravaux devisTravaux = null;
        
        for (int i = 0; i < listeMaisonTravaux.size(); i++) {
            Travaux travaux = travauxService.getByIdTravaux(listeMaisonTravaux.get(i).getIdTravaux());
            devisTravaux = new DevisTravaux();
            devisTravaux.setIdDevis(nouveauDevis.getId());
            devisTravaux.setIdTravaux(travaux.getId());
            devisTravaux.setPrixUnitaire(travaux.getPrixUnitaire());
            devisTravaux.setQuantite(listeMaisonTravaux.get(i).getQuantite());

            listeDevisTravaux.add(devisTravaux);
        }

        devisTravauxService.insertDevisTravaux(listeDevisTravaux);
    }

    public Devis getDevisByIdDevis(int idDevis)
    {
        return devisRepository.findById(idDevis).get();
    }

    public double montantTotalDevis()
    {
        String requete = "SELECT COALESCE(SUM(montant), 0) FROM devis";
        Query query = entityManager.createNativeQuery(requete);

        double montantTotal = Double.parseDouble(query.getSingleResult().toString());

        return montantTotal;
    }


    @Transactional
    public void importDevis()
    {
        devisRepository.importDevis();
    }
}
