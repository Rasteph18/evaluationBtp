package com.projet.evalBtp.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projet.evalBtp.security.Role;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Controller
@RequestMapping("database")
public class DatabaseController {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    @Role(value = {"BTP"})
    @GetMapping("/reset")
    public String reinitialisationBase()
    {
        List<String> listTable = List.of("finition", "unite", "type_travaux", "travaux", "maison", "maison_travaux", "devis", "payement_devis", "devis_travaux", "csv_devis", "csv_maison_travaux", "csv_paiement");

        listTable.forEach(table -> entityManager.createNativeQuery("TRUNCATE TABLE " + table + " CASCADE").executeUpdate());
        entityManager.createNativeQuery("DELETE FROM utilisateur WHERE role != 10").executeUpdate();

        return "redirect:/user/page-login-admin";
    }
}
