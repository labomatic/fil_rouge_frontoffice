package com.fil_rouge_frontoffice.controller.dto;

import com.fil_rouge_frontoffice.entity.Evenement;
import com.fil_rouge_frontoffice.entity.TypeEvenement;
import com.fil_rouge_frontoffice.entity.Utilisateur;

import java.time.LocalDateTime;

public class EvenementDto {
    private Long idEvenement;
    private String intitule;
    private LocalDateTime debut;
    private String description;
    private String lieu;
    private LocalDateTime fin;
    private String mailUtilisateur;

    public EvenementDto() {
    }

    public EvenementDto(Long idEvenement, String intitule, LocalDateTime debut, String description, String lieu, LocalDateTime fin, String mailUtilisateur) {
        this.idEvenement = idEvenement;
        this.intitule = intitule;
        this.debut = debut;
        this.description = description;
        this.lieu = lieu;
        this.fin = fin;
        this.mailUtilisateur = mailUtilisateur;
    }

    public EvenementDto(String intitule, LocalDateTime debut, String description, String lieu, LocalDateTime fin, String mailUtilisateur) {
        this.intitule = intitule;
        this.debut = debut;
        this.description = description;
        this.lieu = lieu;
        this.fin = fin;
        this.mailUtilisateur = mailUtilisateur;
    }

    public Long getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(Long idEvenement) {
        this.idEvenement = idEvenement;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public LocalDateTime getDebut() {
        return debut;
    }

    public void setDebut(LocalDateTime debut) {
        this.debut = debut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }

    public String getMailUtilisateur() {
        return mailUtilisateur;
    }

    public void setMailUtilisateur(String mailUtilisateur) {
        this.mailUtilisateur = mailUtilisateur;
    }

    public Evenement toEvenement(){
        return new Evenement(idEvenement, intitule, debut, description, lieu, fin);
    }
    public static EvenementDto from(Evenement evenement){
        return new EvenementDto(evenement.getIdEvenement(), evenement.getIntitule(), evenement.getDebut(), evenement.getDescription(), evenement.getLieu(), evenement.getFin(), evenement.getUtilisateur().getMail());
    }
}
