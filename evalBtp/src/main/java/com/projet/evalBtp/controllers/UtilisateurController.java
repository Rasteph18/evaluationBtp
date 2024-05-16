package com.projet.evalBtp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.projet.evalBtp.models.Utilisateur;
import com.projet.evalBtp.security.Role;
import com.projet.evalBtp.services.UtilisateurService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UtilisateurController {
    
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/page-login-client")
    public ModelAndView pageLoginClient(@RequestParam(required = false) String errorMessage)
    {
        ModelAndView mv = new ModelAndView("pages/loginClient");

        if (errorMessage != null) {
            mv.addObject("errorMessage", errorMessage);
        }

        return mv;
    }

    @PostMapping("/check-login-client")
    public ModelAndView checkLoginClient(@RequestParam String numero, HttpSession session)
    {
        ModelAndView mv = new ModelAndView("redirect:/devis/liste-devis-client");

        try {
            Utilisateur user = utilisateurService.getUtilisateurByNumero(numero);
            session.setAttribute("user", user);
        } catch (Exception e) {
            mv.addObject("errorMessage", e.getMessage());
            mv.setViewName("redirect:/user/page-login-client");
            return mv;
        }

        return mv;
    }



    @GetMapping("/page-login-admin")
    public ModelAndView pageLoginAdmin(@RequestParam(required = false) String errorMessage)
    {
        ModelAndView mv = new ModelAndView("pages/loginAdmin");

        if (errorMessage != null) {
            mv.addObject("errorMessage", errorMessage);
        }

        return mv;
    }


    @PostMapping("/check-login-admin")
    public ModelAndView checkLoginClient(@RequestParam String mail, @RequestParam String mdp, HttpSession session)
    {
        ModelAndView mv = new ModelAndView("redirect:/devis/page-devis-en-cours");

        try {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setMail(mail);
            utilisateur.setPassword(mdp);
        } catch (Exception e) {
            mv.addObject("errorMessage", e.getMessage());
            mv.setViewName("redirect:/user/page-login-admin");
            return mv;
        }
        

        Utilisateur user = utilisateurService.getUtilisateurByMailEtMdp(mail.trim(), mdp);
        if (user != null) {
            System.out.println("session");
            session.setAttribute("user", user);
        } else {
            mv.addObject("errorMessage", "Vérifier votre mail ou mot de passe");
            mv.setViewName("redirect:/user/page-login-admin");
            return mv;
        }

        return mv;
    }

    @Role(value = {"BTP","CLIENT"})
    @GetMapping("/normal/log-out")
    public String logOut(HttpSession session)
    {
        session.removeAttribute("user");

        return "redirect:/";
    }


    @Role(value = {"BTP"})
    @GetMapping("/admin/log-out")
    public String logOutAdmin(HttpSession session)
    {
        session.removeAttribute("user");

        return "redirect:/user/page-login-admin";
    }

    @GetMapping("/erreur")
    public String nonAutoriser()
    {
        return "pages/notAuthorized";
    }
}
