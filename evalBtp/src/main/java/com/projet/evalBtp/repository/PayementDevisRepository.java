package com.projet.evalBtp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projet.evalBtp.models.PayementDevis;

@Repository
public interface PayementDevisRepository extends JpaRepository<PayementDevis, Integer> {
 
    @Modifying
    @Query(
        value = """
            INSERT INTO payement_devis (referencement, id_devis, montant, date_payement)
            SELECT 
                cp.ref_paiement,
                d.id,
                CAST(cp.montant AS DOUBLE PRECISION),
                to_timestamp(date_paiement, 'DD/MM/YYYY')
            FROM csv_paiement cp
            JOIN devis d ON d.numero = cp.ref_devis
            LEFT JOIN payement_devis pd ON pd.referencement = cp.ref_paiement
            WHERE pd.id IS NULL
                """,
        nativeQuery = true
    )
    public void importPayementDevis();


    public List<PayementDevis> findByIdDevis(int idDevis);
    
}
