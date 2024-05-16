package com.projet.evalBtp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.projet.evalBtp.security.Role;
import com.projet.evalBtp.services.CsvDevisService;
import com.projet.evalBtp.services.CsvMaisonTravauxService;
import com.projet.evalBtp.services.CsvPaiementService;
import com.projet.evalBtp.services.DevisService;
import com.projet.evalBtp.services.DevisTravauxService;
import com.projet.evalBtp.services.FinitionService;
import com.projet.evalBtp.services.ImportDonneeService;
import com.projet.evalBtp.services.MaisonService;
import com.projet.evalBtp.services.MaisonTravauxService;
import com.projet.evalBtp.services.PayementDevisService;
import com.projet.evalBtp.services.TravauxService;
import com.projet.evalBtp.services.UniteService;
import com.projet.evalBtp.services.UtilisateurService;
import com.projet.evalBtp.utils.Erreur;

@Controller
@RequestMapping("import")
public class ImportDonneeController {
    
    @Autowired
    private ImportDonneeService importDonneeService;

    @Autowired
    private CsvPaiementService csvPaiementService;

    @Autowired
    private MaisonService maisonService;

    @Autowired
    private UniteService uniteService;

    @Autowired
    private TravauxService travauxService;

    @Autowired
    private MaisonTravauxService maisonTravauxService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private FinitionService finitionService;

    @Autowired
    private DevisService devisService;

    @Autowired
    private PayementDevisService payementDevisService;


    @Autowired
    private DevisTravauxService devisTravauxService;

    @Autowired
    private CsvMaisonTravauxService csvMaisonTravauxService;

    @Autowired
    private CsvDevisService csvDevisService;

    @Role(value = {"BTP"})
    @GetMapping("/maison-travaux-devis")
    public ModelAndView pageImportMaisonTravauxDevis()
    {
        ModelAndView mv = new ModelAndView("pages/importMaisonTravauxDevis");


        return mv;
    }

    @Transactional
    @Role(value = {"BTP"})
    @PostMapping("/import-donnee-maison-travaux-devis")
    public ModelAndView importDonneeMaisonTravauxDevis(@RequestParam MultipartFile maisonTravaux, @RequestParam MultipartFile devis)
    {
        ModelAndView mv = new ModelAndView("redirect:/devis/dashboard");

        List<Erreur> listeErreur = importDonneeService.insertMaisonTravauDevis(maisonTravaux, devis);
        if (listeErreur.size() > 0) {
            mv.addObject("listeErreur", listeErreur);
            mv.setViewName("pages/listeErreur");

            return mv;
        }

        maisonService.importMaison();
        uniteService.importUnite();
        travauxService.importTravaux();
        maisonTravauxService.importMaisonTravaux();
        utilisateurService.importUtilisateur();
        finitionService.importFinition();
        devisService.importDevis();
        devisTravauxService.importDevisTravaux();

        csvDevisService.viderCsvDevis();
        csvMaisonTravauxService.viderCsvMaisonTravaux();

        return mv;
    }

    @Role(value = {"BTP"})
    @GetMapping("/paiement")
    public ModelAndView pageImportPaiement()
    {
        ModelAndView mv = new ModelAndView("pages/importationPaiement");

        return mv;
    }


    @Transactional
    @Role(value = {"BTP"})
    @PostMapping("/import-paiement")
    public ModelAndView importPaiement(@RequestParam MultipartFile paiement)
    {
        ModelAndView mv = new ModelAndView("redirect:/devis/dashboard");

        List<Erreur> listeErreur = csvPaiementService.insertPaiementCsv(paiement);
        if (listeErreur.size() > 0) {
            mv.addObject("listeErreur", listeErreur);
            mv.setViewName("pages/listeErreur");

            return mv;
        }
        payementDevisService.importPayementDevis();

        csvPaiementService.viderCsvPaiement();

        return mv;
    }
}
