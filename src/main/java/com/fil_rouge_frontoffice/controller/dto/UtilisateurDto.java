package com.fil_rouge_frontoffice.controller.dto;

import com.fil_rouge_frontoffice.entity.Evenement;
import com.fil_rouge_frontoffice.entity.Role;
import com.fil_rouge_frontoffice.entity.StatutCompte;
import com.fil_rouge_frontoffice.entity.Utilisateur;
import jakarta.persistence.*;
import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurDto {

    private Long idUtilisateur;

    private String prenom;

    private String mail;

    private String ville;

    private String pays;

    private String photo;

    private String nom;

    private List<Evenement> planning = new ArrayList<>();

    public UtilisateurDto() {}

    public UtilisateurDto(Long id, String nom, String prenom, String mail,String ville, String pays, Role role, StatutCompte statutCompte, List<Evenement> planning, String photo) {
        this.idUtilisateur = id;
        this.prenom = prenom;
        this.mail = mail;
        this.ville = ville;
        this.pays = pays;
        this.nom = nom;
        this.planning = planning;
        this.photo = photo;
    }

    public static UtilisateurDto from(Utilisateur u) {
        UtilisateurDto dto = new UtilisateurDto();
        dto.setIdUtilisateur(u.getIdUtilisateur());
        dto.setPrenom(u.getPrenom());
        dto.setNom(u.getNom());
        dto.setMail(u.getMail());
        dto.setVille(u.getVille());
        dto.setPays(u.getPays());
        dto.setPhoto(u.getPhoto());
        dto.setPlanning(u.getPlanning());
        return dto;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Evenement> getPlanning() {
        return planning;
    }

    public void setPlanning(List<Evenement> planning) {
        this.planning = planning;
    }
}
