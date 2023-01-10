package com.fil_rouge_frontoffice.repository;


import com.fil_rouge_frontoffice.entity.AvoirDroitsCrudPlanningAutreUtilisateur;
import com.fil_rouge_frontoffice.entity.ClePartagePlanning;
import com.fil_rouge_frontoffice.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AvoirDroitsCrudPlanningAutreUtilisateurRepository extends JpaRepository<AvoirDroitsCrudPlanningAutreUtilisateur, ClePartagePlanning> {

    @Query(value = "delete from AvoirDroitsCrudPlanningAutreUtilisateur droits where droits.id.idProprietaire =:idUtilisateur or droits.id.idAyantDroit = :idUtilisateur", nativeQuery = true)
    void supprimerRelationsUtilisateur(@Param("idUtilisateur") Long idUtilisateur);

    List<AvoirDroitsCrudPlanningAutreUtilisateur> findAvoirDroitsCrudPlanningAutreUtilisateursByAyantDroitOrProprietaire(Utilisateur proprietaire, Utilisateur ayantDroit);
}
