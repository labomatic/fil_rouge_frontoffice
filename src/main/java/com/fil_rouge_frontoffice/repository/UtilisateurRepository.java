package com.fil_rouge_frontoffice.repository;

import com.fil_rouge_frontoffice.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Boolean existsByMail(String email);

    Optional<Utilisateur> findUtilisateurByMail(String email);


}
