package com.projet.evalBtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import com.projet.evalBtp.models.Unite;

@Controller
public interface UniteRepository extends JpaRepository<Unite, Integer> {
    
}
