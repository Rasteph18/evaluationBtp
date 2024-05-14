package com.projet.evalBtp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.VDetailsDevisUser;

@Repository
public interface VDetailsDevisUserRepository extends JpaRepository<VDetailsDevisUser, Integer> {
    
    public List<VDetailsDevisUser> findByIdUser(int idUser);
}
