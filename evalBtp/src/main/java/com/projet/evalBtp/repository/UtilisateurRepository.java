package com.projet.evalBtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    
    @Query(
        value = "SELECT * FROM utilisateur WHERE numero ILIKE :numero",
        nativeQuery = true
    )
    public Utilisateur findByNumero(String numero);


    public Utilisateur findByMailAndPassword(String mail, String password);
}
