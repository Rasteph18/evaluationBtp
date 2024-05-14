package com.projet.evalBtp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.projet.evalBtp.models.VStatMontantDevisMoisAnnee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class VStatMontantDevisMoisAnneeService {
    
    @PersistenceContext
    private EntityManager entityManager;

    public List<VStatMontantDevisMoisAnnee> getStatMontantByAnnee(int annee)
    {
        String requete = """
            WITH months AS (
                SELECT generate_series(1, 12) AS month
            )
            SELECT 
                months.month,
                :annee AS year,
                COALESCE(SUM(d.montant), 0) AS total_montant
            FROM 
                months
            LEFT JOIN 
                devis d ON months.month = EXTRACT(MONTH FROM d.date_devis) 
                       AND EXTRACT(YEAR FROM d.date_devis) = :annee  
            GROUP BY 
                months.month
            ORDER BY 
                months.month;
                """;
        Query query = entityManager.createNativeQuery(requete);
        query.setParameter("annee", annee);

        List<Object[]> resultList = query.getResultList();
        
        return resultList.stream().map(result -> new VStatMontantDevisMoisAnnee(
            ((Number) result[0]).intValue(),
            ((Number) result[1]).intValue(),
            ((Number) result[2]).doubleValue()
        )).collect(Collectors.toList());
    }
}
