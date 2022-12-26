package com.fil_rouge_frontoffice.controller.dto;

import com.fil_rouge_frontoffice.entity.Role;
import com.fil_rouge_frontoffice.entity.StatutCompte;
import com.fil_rouge_frontoffice.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

public class SignupRequest {

    private String prenom;

    private String mail;

    private String password;

    private String ville;

    private String pays;

    private MultipartFile photo;

    private String nom;

    public SignupRequest() {
    }

    public SignupRequest(String mail, String password, String prenom, String nom, String ville, String pays, MultipartFile photo) {
        this.mail = mail;
        this.password = password;
        this.prenom = prenom;
        this.nom = nom;
        this.ville = ville;
        this.pays = pays;
        this.photo = photo;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Utilisateur toUtilisateur() {
        String photoName = photo.getOriginalFilename();
        int index = photoName.lastIndexOf(".");
        String photoExtension = photoName.substring(index);

        return new Utilisateur(nom, prenom, mail, password, ville, pays, mail + photoExtension, null, null);
    }
}

