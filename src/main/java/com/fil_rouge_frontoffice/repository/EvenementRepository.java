package com.fil_rouge_frontoffice.repository;

import com.fil_rouge_frontoffice.entity.Evenement;
import com.fil_rouge_frontoffice.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {
    Boolean existsByIdEvenement(Long id);

    @Query("select e from Evenement e where e.utilisateur.mail = :mail and e.debut >= :now order by e.debut asc")
    List<Evenement> findUpcomingEvenementsUtilisateur(@Param("mail") String mail, @Param("now") LocalDateTime now);
}
