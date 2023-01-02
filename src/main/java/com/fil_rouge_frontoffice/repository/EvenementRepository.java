package com.fil_rouge_frontoffice.repository;

import com.fil_rouge_frontoffice.entity.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {
    Boolean existsByIdEvenement(Long id);
}
