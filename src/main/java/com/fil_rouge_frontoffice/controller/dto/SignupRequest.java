package com.fil_rouge_frontoffice.controller.dto;

public class SignupRequest {

    private String prenom;

    private String mail;

    private String password;

    private String ville;

    private String pays;

    private String photo;

    private String nom;

    public SignupRequest() {}

    public SignupRequest(String mail, String password, String prenom, String nom, String ville, String pays, String photo) {
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
}
