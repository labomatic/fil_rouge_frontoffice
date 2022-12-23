package com.fil_rouge_frontoffice.repository;

import com.fil_rouge_frontoffice.entity.StatutCompte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutCompteRepository extends JpaRepository<StatutCompte, Long> {

    StatutCompte findStatutCompteByIntitule(String intitule);

}
