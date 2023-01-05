package com.fil_rouge_frontoffice.controller.dto;

import com.fil_rouge_frontoffice.entity.AvoirDroitsCrudPlanningAutreUtilisateur;

public class AvoirDroitsCrudPlanningAutreUtilisateurDto {
    private String mailProprietaire;
    private String mailAyantDroit;
    private boolean peutEcrire;
    private boolean peutLire;
    private boolean peutModifier;
    private boolean peutSupprimer;

    public AvoirDroitsCrudPlanningAutreUtilisateurDto() {
    }

    public AvoirDroitsCrudPlanningAutreUtilisateurDto(String mailProprietaire, String mailAyantDroit, boolean peutEcrire, boolean peutLire, boolean peutModifier, boolean peutSupprimer) {
        this.mailProprietaire = mailProprietaire;
        this.mailAyantDroit = mailAyantDroit;
        this.peutEcrire = peutEcrire;
        this.peutLire = peutLire;
        this.peutModifier = peutModifier;
        this.peutSupprimer = peutSupprimer;
    }

    public static AvoirDroitsCrudPlanningAutreUtilisateurDto from(AvoirDroitsCrudPlanningAutreUtilisateur ad){
        if(ad == null) return new AvoirDroitsCrudPlanningAutreUtilisateurDto("", "", false, false, false, false);
        return new AvoirDroitsCrudPlanningAutreUtilisateurDto(ad.getProprietaire().getMail(), ad.getAyantDroit().getMail(), ad.getPeutCreer() == true, ad.getPeutLire() == true, ad.getPeutModifier() == true, ad.getPeutSupprimer() == true);
    }

    public String getMailProprietaire() {
        return mailProprietaire;
    }

    public void setMailProprietaire(String mailProprietaire) {
        this.mailProprietaire = mailProprietaire;
    }

    public String getMailAyantDroit() {
        return mailAyantDroit;
    }

    public void setMailAyantDroit(String mailAyantDroit) {
        this.mailAyantDroit = mailAyantDroit;
    }

    public boolean isPeutEcrire() {
        return peutEcrire;
    }

    public void setPeutEcrire(boolean peutEcrire) {
        this.peutEcrire = peutEcrire;
    }

    public boolean isPeutLire() {
        return peutLire;
    }

    public void setPeutLire(boolean peutLire) {
        this.peutLire = peutLire;
    }

    public boolean isPeutModifier() {
        return peutModifier;
    }

    public void setPeutModifier(boolean peutModifier) {
        this.peutModifier = peutModifier;
    }

    public boolean isPeutSupprimer() {
        return peutSupprimer;
    }

    public void setPeutSupprimer(boolean peutSupprimer) {
        this.peutSupprimer = peutSupprimer;
    }
}
