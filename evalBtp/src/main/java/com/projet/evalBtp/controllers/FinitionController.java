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

import com.projet.evalBtp.models.Finition;
import com.projet.evalBtp.security.Role;
import com.projet.evalBtp.services.FinitionService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("finition")
public class FinitionController {
    
    @Autowired
    public FinitionService finitionService;

    @Role(value = {"BTP"})
    @GetMapping("/page-all-finition")
    public ModelAndView pageAllFinition(@RequestParam(defaultValue = "0") int numPage)
    {
        ModelAndView mv = new ModelAndView("pages/listeFinition");
        int size = 10;

        Pageable pageable = PageRequest.of(numPage, size);
        Page<Finition> allFinition = finitionService.getAllFinition(pageable);

        mv.addObject("allFinition", allFinition.getContent());
        mv.addObject("nbPage", allFinition.getTotalPages());
        mv.addObject("numPage", numPage);


        return mv;
    }


    @Role(value = {"BTP"})
    @GetMapping("/page-modif-finition")
    public ModelAndView pageModifFinition(@RequestParam int idFinition, @RequestParam(required = false) String errorMessage)
    {
        ModelAndView mv = new ModelAndView("pages/modificationFinition");
        Finition finition = finitionService.getByIdFinition(idFinition);

        mv.addObject("finition", finition);

        if (errorMessage != null) {
            mv.addObject("errorMessage", errorMessage);
        }

        return mv;
    }


    @Role(value = {"BTP"})
    @PostMapping("/modif-finition")
    public ModelAndView modifFinition(HttpServletRequest request)
    {
        ModelAndView mv = new ModelAndView("redirect:/finition/page-all-finition");

        try {
            Finition finition = new Finition();
            finition.setId(Integer.parseInt(request.getParameter("id")));
            finition.setNom(request.getParameter("nom"));
            finition.setMarge(Double.parseDouble(request.getParameter("marge")));
            finition.setEtat(Integer.parseInt(request.getParameter("etat")));

            finitionService.saveFinition(finition);
            
        } catch (Exception e) {
            mv.addObject("errorMessage", e.getMessage());
            mv.addObject("idFinition", request.getParameter("id"));
            mv.setViewName("redirect:/finition/page-modif-finition");
            return mv;
        }

        return mv;
    }
}
