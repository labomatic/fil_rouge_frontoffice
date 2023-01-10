package com.fil_rouge_frontoffice.repository;

import com.fil_rouge_frontoffice.entity.AvoirDroitsCrudPlanningAutreUtilisateur;
import com.fil_rouge_frontoffice.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Boolean existsByMail(String email);

    Optional<Utilisateur> findUtilisateurByMail(String email);


    @Query("select u from Utilisateur u where u.mail like :string% or u.nom like :string% or u.prenom like :string%")
    List<Utilisateur> searchUtilisateursByKeyword(@Param("string") String string);

    @Query("select ad from AvoirDroitsCrudPlanningAutreUtilisateur ad where ad.proprietaire.mail = :mailProprietaire and ad.ayantDroit.mail = :mailUtilisateurConnecte and ad.peutLire = true")
    List<AvoirDroitsCrudPlanningAutreUtilisateur> peutLire(@Param("mailProprietaire") String mailProprietaire, @Param("mailUtilisateurConnecte") String mailUtilisateurConnecte);

    @Query("select ad from AvoirDroitsCrudPlanningAutreUtilisateur ad where ad.proprietaire.mail = :mailProprietaire and ad.ayantDroit.mail = :mailUtilisateurConnecte and ad.peutCreer = true")
    List<AvoirDroitsCrudPlanningAutreUtilisateur> peutCreer(@Param("mailProprietaire") String mailProprietaire, @Param("mailUtilisateurConnecte") String mailUtilisateurConnecte);

    @Query("select ad from AvoirDroitsCrudPlanningAutreUtilisateur ad where ad.proprietaire.mail = :mailProprietaire and ad.ayantDroit.mail = :mailUtilisateurConnecte and ad.peutModifier = true")
    List<AvoirDroitsCrudPlanningAutreUtilisateur> peutModifier(@Param("mailProprietaire") String mailProprietaire, @Param("mailUtilisateurConnecte") String mailUtilisateurConnecte);

    @Query("select ad from AvoirDroitsCrudPlanningAutreUtilisateur ad where ad.proprietaire.mail = :mailProprietaire and ad.ayantDroit.mail = :mailUtilisateurConnecte and ad.peutSupprimer = true")
    List<AvoirDroitsCrudPlanningAutreUtilisateur> peutSupprimer(@Param("mailProprietaire") String mailProprietaire, @Param("mailUtilisateurConnecte") String mailUtilisateurConnecte);

    @Query("select ad from AvoirDroitsCrudPlanningAutreUtilisateur ad where ad.proprietaire.mail = :mailProprietaire and ad.ayantDroit.mail = :mailAyantDroit")
    Optional<AvoirDroitsCrudPlanningAutreUtilisateur> getDroits(@Param("mailProprietaire") String mailProprietaire, @Param("mailAyantDroit") String mailAyantDroit);

    @Query("update AvoirDroitsCrudPlanningAutreUtilisateur ad set ad.peutLire = :nouvelleValeur where ad.proprietaire.mail = :mailProprietaire and ad.ayantDroit.mail = :mailAyantDroit")
    void switchDroitLecture(@Param("mailProprietaire") String mailProprietaire, @Param("mailAyantDroit") String mailAyantDroit, @Param("nouvelleValeur") boolean nouvelleValeur);
    @Query("select ad from AvoirDroitsCrudPlanningAutreUtilisateur ad where ad.proprietaire.mail = :mailProprietaire and ad.ayantDroit.mail= :mailAyantDroit")
    Optional<AvoirDroitsCrudPlanningAutreUtilisateur> findRelation(@Param("mailProprietaire") String mailProprietaire, @Param("mailAyantDroit") String mailAyantDroit);
    @Query(value = "insert into AvoirDroitsCrudPlanningAutreUtilisateur(proprietaire, ayantDroit, peutLire, peutCreer, peutModifier, peutSupprimer) values(:proprietaire, :ayantDroit, :peutLire, :peutEcrire, :peutModifier, :peutSupprimer)", nativeQuery = true)
    void creerRelation(@Param("proprietaire") Utilisateur proprietaire, @Param("ayantDroit") Utilisateur ayantDroit, @Param("peutLire") boolean peutLire,@Param("peutEcrire") boolean peutEcrire, @Param("peutModifier") boolean peutModifier, @Param("peutSupprimer") boolean peutSupprimer);

}
