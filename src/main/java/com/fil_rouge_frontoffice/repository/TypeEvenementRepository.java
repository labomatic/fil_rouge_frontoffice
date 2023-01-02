package com.fil_rouge_frontoffice.repository;

import com.fil_rouge_frontoffice.entity.TypeEvenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeEvenementRepository extends JpaRepository<TypeEvenement, Long> {
    TypeEvenement findTypeEvenementByIntitule(String intitule);
}
