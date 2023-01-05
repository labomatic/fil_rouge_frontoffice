package com.fil_rouge_frontoffice.controller.dto;

public class RequeteDroitsDto {
    private String mailProprietaire;
    private String mailAyantDroit;

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
}
