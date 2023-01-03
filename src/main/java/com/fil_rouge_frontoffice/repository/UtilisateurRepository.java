package com.fil_rouge_frontoffice.repository;

import com.fil_rouge_frontoffice.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Boolean existsByMail(String email);

    Optional<Utilisateur> findUtilisateurByMail(String email);

    @Query("select u from Utilisateur u where u.mail like :string% or u.nom like :string% or u.prenom like :string%")
    List<Utilisateur> searchUtilisateursByKeyword(@Param("string") String string);


}
