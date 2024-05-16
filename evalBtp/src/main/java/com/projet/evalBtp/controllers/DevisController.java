package com.projet.evalBtp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.projet.evalBtp.models.PayementDevis;
import com.projet.evalBtp.models.Utilisateur;
import com.projet.evalBtp.models.VDevisEnCours;
import com.projet.evalBtp.models.VPdfDevis;
import com.projet.evalBtp.security.Role;
import com.projet.evalBtp.services.DevisService;
import com.projet.evalBtp.services.FinitionService;
import com.projet.evalBtp.services.PayementDevisService;
import com.projet.evalBtp.services.VDetailsDevisUserService;
import com.projet.evalBtp.services.VDevisEnCoursService;
import com.projet.evalBtp.services.VPdfDevisService;
import com.projet.evalBtp.services.VPrixMaisonService;
import com.projet.evalBtp.services.VStatMontantDevisMoisAnneeService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("devis")
public class DevisController {
    

    @Autowired
    private TemplateEngine templateEngine;


    @Autowired
    private FinitionService finitionService;

    @Autowired
    private VPrixMaisonService vPrixMaisonService;

    @Autowired
    private DevisService devisService;

    @Autowired
    private VDetailsDevisUserService vDetailsDevisUserService;

    @Autowired
    private VPdfDevisService vPdfDevisService;

    @Autowired
    private VDevisEnCoursService vDevisEnCoursService;

    @Autowired
    private VStatMontantDevisMoisAnneeService vStatMontantDevisMoisAnneeService;

    @Autowired
    private PayementDevisService payementDevisService;

    @Role(value = {"BTP", "CLIENT"})
    @GetMapping("/liste-devis-client")
    public ModelAndView ListeDevisClient(HttpSession session)
    {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        ModelAndView mv = new ModelAndView("pages/listeDevisClient");

        mv.addObject("allDevis", vDetailsDevisUserService.getByIdUser(user.getId()));

        return mv;
    }

    @Role(value = {"BTP", "CLIENT"})
    @GetMapping("/page-nouveau-devis-client")
    public ModelAndView pageCreationNouveauDevis(@RequestParam(required = false) String errorMessage)
    {
        ModelAndView mv = new ModelAndView("pages/creationDevisClient");

        if (errorMessage != null) {
            mv.addObject("errorMessage", errorMessage);
        }

        mv.addObject("allMaison", vPrixMaisonService.getAllPrixMaison());
        mv.addObject("allFinition", finitionService.getAllFinition());

        return mv;
    }

    @Role(value = {"BTP", "CLIENT"})
    @PostMapping("/insert-nouveau-devis")
    public ModelAndView insertNouveauDevis(@RequestParam String typeMaison, @RequestParam String typeFinition, @RequestParam String dateDebut, @RequestParam(required = false) String lieu, HttpSession session)
    {
        ModelAndView mv = new ModelAndView("redirect:/devis/liste-devis-client");

        try {
            Utilisateur user = (Utilisateur)session.getAttribute("user");
            int idTypeMaison = Integer.parseInt(typeMaison);
            int idTypeFInition = Integer.parseInt(typeFinition);

            devisService.insertDevisEtDevisTravaux(idTypeMaison, idTypeFInition, user.getId(), dateDebut, lieu);

        } catch (Exception e) {
            mv.addObject("errorMessage", e.getMessage());
            mv.setViewName("redirect:/devis/page-nouveau-devis-client");
            return mv;
        }


        return mv;
    }


    @Role(value = {"BTP", "CLIENT"})
    @GetMapping("/pdf-devis")
    public ResponseEntity<?> exportPdf(HttpServletResponse response, Model model, @RequestParam int idDevis)
    {
        try {
            Context context = new Context();
            model.addAttribute("detailsDevis", vDetailsDevisUserService.getById(idDevis));
            model.addAttribute("detailsTravaux", vPdfDevisService.getAllDetailsDevisByIdDevis(idDevis));


            double sommePayer = 0;
            List<PayementDevis> listePayementDevis = payementDevisService.getByIdDevis(idDevis); 
            for (int i = 0; i < listePayementDevis.size(); i++) {
                sommePayer = sommePayer + listePayementDevis.get(i).getMontant();
            }

            model.addAttribute("listePayement", listePayementDevis);
            model.addAttribute("sommePayer", sommePayer);

            context.setVariables(model.asMap());

            String html = templateEngine.process("pages/devisPdf", context);
            ByteArrayOutputStream target = new ByteArrayOutputStream();
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setBaseUri("http://localhost:8080");
            HtmlConverter.convertToPdf(html, target, converterProperties);
            byte[] bytes = target.toByteArray();

            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=devis" + currentDateTime + ".pdf") 
                .contentType(MediaType.APPLICATION_PDF) 
                .body(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.notFound().build();
    }


    @Role(value = {"BTP"})
    @GetMapping("/page-devis-en-cours")
    public ModelAndView pageDevisEnCours(@RequestParam(required = false, defaultValue = "0") int numPage)
    {
        ModelAndView mv = new ModelAndView("pages/devisEnCours");
        int size = 10;
        Pageable pageable = PageRequest.of(numPage, size);

        Page<VDevisEnCours> getDevisEnCours = vDevisEnCoursService.getAllDevisEnCours(pageable);

        mv.addObject("listeDevis", getDevisEnCours.getContent());
        mv.addObject("nbPage", getDevisEnCours.getTotalPages());
        mv.addObject("numPage", numPage);


        return mv;
    }

    @Role(value = {"BTP"})
    @GetMapping("/page-travaux-a-effectuer")
    public ModelAndView pageTravauxAEffectuer(@RequestParam int idDevis, @RequestParam(required = false, defaultValue = "0") int numPage)
    {
        ModelAndView mv = new ModelAndView("pages/travauxAEffectuer");

        int size = 10;
        Pageable pageable = PageRequest.of(numPage, size);

        Page<VPdfDevis> allTravaux = vPdfDevisService.getAllDetailsDevisByIdDevis(idDevis, pageable);

        mv.addObject("listeTravaux", allTravaux.getContent());
        mv.addObject("nbPage", allTravaux.getTotalPages());
        mv.addObject("numPage", numPage);
        mv.addObject("idDevis", idDevis);

        return mv;
    }


    @Role(value = {"BTP"})
    @GetMapping("/dashboard")
    public ModelAndView dashboard(@RequestParam(defaultValue = "2024") int annee)
    {
        ModelAndView mv = new ModelAndView("pages/dashboard");

        mv.addObject("montantTotalDevis", devisService.montantTotalDevis());
        mv.addObject("statistiques", vStatMontantDevisMoisAnneeService.getStatMontantByAnnee(annee));
        mv.addObject("montantTotalPaiementEffectue", payementDevisService.montantTotalPaiementEffectue());
        mv.addObject("annee", annee); 

        return mv;
    }
}
