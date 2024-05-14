package com.projet.evalBtp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.projet.evalBtp.models.Travaux;
import com.projet.evalBtp.models.Unite;
import com.projet.evalBtp.security.Role;
import com.projet.evalBtp.services.TravauxService;
import com.projet.evalBtp.services.UniteService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("travaux")
public class TravauxController {
    
    @Autowired
    private TravauxService travauxService;

    @Autowired
    private UniteService uniteService;

    @Role(value = {"BTP"})
    @GetMapping("/page-all-travaux")
    public ModelAndView pageAllTravaux(@RequestParam(defaultValue = "0") int numPage)
    {
        ModelAndView mv = new ModelAndView("pages/listeTravaux");

        int size = 10;

        Pageable pageable = PageRequest.of(numPage, size);
        Page<Travaux> allTravaux = travauxService.getAllTravaux(pageable);

        mv.addObject("allTravaux", allTravaux.getContent());
        mv.addObject("nbPage", allTravaux.getTotalPages());
        mv.addObject("numPage", numPage);

        return mv;
    }

    @Role(value = {"BTP"})
    @GetMapping("/page-modif-travaux")
    public ModelAndView pageModifTravaux(@RequestParam int idTravaux, @RequestParam(required = false) String errorMessage)
    {
        ModelAndView mv = new ModelAndView("pages/modificationTravaux");
        Travaux travaux = travauxService.getByIdTravaux(idTravaux);

        mv.addObject("travaux", travaux);
        mv.addObject("allUnite", uniteService.getAllUnite());
        if (errorMessage != null) {
            mv.addObject("errorMessage", errorMessage);
        }

        return mv;
    }


    @Role(value = {"BTP"})
    @PostMapping("/modif-travaux")
    public ModelAndView modifTravaux(HttpServletRequest request)
    {
        ModelAndView mv = new ModelAndView("redirect:/travaux/page-all-travaux");

        try {
            Travaux travaux = new Travaux();
            travaux.setId(Integer.parseInt(request.getParameter("id")));
            travaux.setIdTypeTravaux(null);
            travaux.setEtat(Integer.parseInt(request.getParameter("etat")));
            travaux.setCode(request.getParameter("code"));
            travaux.setNomTravaux(request.getParameter("nomTravaux"));
            Unite unite = uniteService.getUniteById(Integer.parseInt(request.getParameter("unite")));
            travaux.setUnite(unite);
            travaux.setPrixUnitaire(Double.parseDouble(request.getParameter("prixUnitaire")));

            travauxService.saveTravaux(travaux);
            
        } catch (Exception e) {
            mv.addObject("errorMessage", e.getMessage());
            mv.addObject("idTravaux", request.getParameter("id"));
            mv.setViewName("redirect:/travaux/page-modif-travaux");
            return mv;
        }
        

        return mv;
    }
}
