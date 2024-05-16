package com.projet.evalBtp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import com.projet.evalBtp.models.PayementDevis;
import com.projet.evalBtp.security.Role;
import com.projet.evalBtp.services.DevisService;
import com.projet.evalBtp.services.PayementDevisService;

@Controller
@RequestMapping("payement")
public class PayementController {
    
    @Autowired
    private PayementDevisService payementDevisService;

    @Autowired
    private DevisService devisService;

    @Role(value = {"BTP", "CLIENT"})
    @GetMapping("/page-payement-devis")
    public ModelAndView pagePayementDevis(@RequestParam(required = false) String errorMessage, @RequestParam String idDevis)
    {
        ModelAndView mv = new ModelAndView("pages/payementDevis");

        mv.addObject("idDevis", idDevis);

        if (errorMessage != null) {
            mv.addObject("errorMessage", errorMessage);
        }

        return mv;
    }

    @Role(value = {"BTP", "CLIENT"})
    @PostMapping("/insert-payement-devis")
    public ModelAndView insertPayementDevis(@RequestParam String idDevis, @RequestParam String montant, @RequestParam String datePayement)
    {
        ModelAndView mv = new ModelAndView("redirect:/devis/liste-devis-client");

        try {
            double montantTotal = devisService.getDevisByIdDevis(Integer.parseInt(idDevis)).getMontant();
            
            double sommePayer = payementDevisService.getSommePayerByIdDevis(Integer.parseInt(idDevis)) + Double.parseDouble(montant);

            if (montantTotal < sommePayer) {
                throw new Exception("Vous avez depassez la total de votre montant");
            }

        
            payementDevisService.insertPayement(idDevis, montant, datePayement);
        } catch (Exception e) {
            mv.addObject("errorMessage", e.getMessage());
            mv.addObject("idDevis", idDevis);
            mv.setViewName("redirect:/payement/page-payement-devis");
            return mv;
        }

        return mv;
    }


    @Role(value = {"BTP", "CLIENT"})
    @PostMapping("/validation-paiement-ajax")
    public ResponseEntity<Map<String ,Object>> validationPaiementAjax(@RequestParam String idDevis, @RequestParam String montant, @RequestParam String datePayement)
    {
        System.out.println("ato --------------------------------");
        PayementDevis payementDevis = new PayementDevis();
        Map<String, Object> response = new HashMap<>();
        try {
            payementDevis.setIdDevis(Integer.parseInt(idDevis));
            payementDevis.setMontant(montant);
            payementDevis.setDatePayement(datePayement);

            double montantTotal = devisService.getDevisByIdDevis(Integer.parseInt(idDevis)).getMontant();
            
            double sommePayer = payementDevisService.getSommePayerByIdDevis(Integer.parseInt(idDevis)) + Double.parseDouble(montant);

            if (montantTotal < sommePayer) {
                throw new Exception("Vous avez depassez la total de votre montant total a payer.Reste a payer: " + (montantTotal-payementDevisService.getSommePayerByIdDevis(Integer.parseInt(idDevis))));
            }
            payementDevisService.insertPayement(idDevis, montant, datePayement);

            response.put("valid", true);
        } catch (Exception e) {
            response.put("valid", false);
            response.put("message", e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
    
}
