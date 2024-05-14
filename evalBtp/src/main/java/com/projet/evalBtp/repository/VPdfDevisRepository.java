package com.projet.evalBtp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.VPdfDevis;

@Repository
public interface VPdfDevisRepository extends JpaRepository<VPdfDevis, Integer> {
    
    public List<VPdfDevis> findByIdDevis(int idDevis);
    public Page<VPdfDevis> findByIdDevis(int idDevis, Pageable pageable);
}
