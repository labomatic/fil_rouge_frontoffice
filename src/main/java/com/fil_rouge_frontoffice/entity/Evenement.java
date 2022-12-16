package com.fil_rouge_frontoffice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "evenement", schema = "plannings_meteo", catalog = "")
public class Evenement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_evenement", nullable = false)
    private Long idEvenement;

    @Column(name = "intitule", nullable = false, length = 50)
    private String intitule;

    @Column(name = "debut", nullable = false)
    private LocalDateTime debut;

    @Column(name = "description", nullable = true, length = 255)
    private String description;

    @Column(name = "lieu", nullable = true, length = 150)
    private String lieu;

    @Column(name = "fin", nullable = true)
    private LocalDateTime fin;

    @ManyToOne
    @JoinColumn(name = "id_proprietaire")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_type_evenement")
    private TypeEvenement typeEvenement;

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

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public TypeEvenement getTypeEvenement() {
        return typeEvenement;
    }

    public void setTypeEvenement(TypeEvenement typeEvenement) {
        this.typeEvenement = typeEvenement;
    }

}