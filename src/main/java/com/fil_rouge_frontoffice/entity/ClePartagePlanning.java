package com.fil_rouge_frontoffice.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ClePartagePlanning implements Serializable {
    private Long idProprietaire;
    private Long idAyantDroit;

    public ClePartagePlanning() {
    }

    public ClePartagePlanning(Long idProprietaire, Long idAyantDroit) {
        this.idProprietaire = idProprietaire;
        this.idAyantDroit = idAyantDroit;
    }

    public Long getIdProprietaire() {
        return idProprietaire;
    }

    public void setIdProprietaire(Long idProprietaire) {
        this.idProprietaire = idProprietaire;
    }

    public Long getIdAyantDroit() {
        return idAyantDroit;
    }

    public void setIdAyantDroit(Long idAyantDroit) {
        this.idAyantDroit = idAyantDroit;
    }
}
