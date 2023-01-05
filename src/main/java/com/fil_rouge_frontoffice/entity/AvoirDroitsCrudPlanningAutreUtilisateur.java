package com.fil_rouge_frontoffice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "avoir_droits_crud_planning_autre_utilisateur", schema = "plannings_meteo")
public class AvoirDroitsCrudPlanningAutreUtilisateur {

    @ManyToOne
    @JoinColumn(name = "id_proprietaire")
    @Id
    private Utilisateur proprietaire;

    @ManyToOne
    @JoinColumn(name = "id_ayant_droit")
    @Id
    private Utilisateur ayantDroit;

    @Column(name = "peut_lire", nullable = true)
    private Boolean peutLire;

    @Column(name = "peut_creer", nullable = true)
    private Boolean peutCreer;

    @Column(name = "peut_modifier", nullable = true)
    private Boolean peutModifier;

    @Column(name = "peut_supprimer", nullable = true)
    private Boolean peutSupprimer;

    public AvoirDroitsCrudPlanningAutreUtilisateur() {
    }

    public AvoirDroitsCrudPlanningAutreUtilisateur(Utilisateur proprietaire, Utilisateur ayantDroit, Boolean peutLire, Boolean peutCreer, Boolean peutModifier, Boolean peutSupprimer) {
        this.proprietaire = proprietaire;
        this.ayantDroit = ayantDroit;
        this.peutLire = peutLire;
        this.peutCreer = peutCreer;
        this.peutModifier = peutModifier;
        this.peutSupprimer = peutSupprimer;
    }

    public Utilisateur getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Utilisateur proprietaire) {
        this.proprietaire = proprietaire;
    }

    public Utilisateur getAyantDroit() {
        return ayantDroit;
    }

    public void setAyantDroit(Utilisateur ayantDroit) {
        this.ayantDroit = ayantDroit;
    }

    public Boolean getPeutLire() {
        return peutLire;
    }

    public void setPeutLire(Boolean peutLire) {
        this.peutLire = peutLire;
    }

    public Boolean getPeutCreer() {
        return peutCreer;
    }

    public void setPeutCreer(Boolean peutCreer) {
        this.peutCreer = peutCreer;
    }

    public Boolean getPeutModifier() {
        return peutModifier;
    }

    public void setPeutModifier(Boolean peutModifier) {
        this.peutModifier = peutModifier;
    }

    public Boolean getPeutSupprimer() {
        return peutSupprimer;
    }

    public void setPeutSupprimer(Boolean peutSupprimer) {
        this.peutSupprimer = peutSupprimer;
    }


}