package com.fil_rouge_frontoffice.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "type_evenement", schema = "plannings_meteo")
public class TypeEvenement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_type_evenement", nullable = false)
    private Long idTypeEvenement;

    @Column(name = "intitule", nullable = false, length = 50)
    private String intitule;

    @OneToMany (targetEntity = Evenement.class, mappedBy = "typeEvenement")
    private List<Evenement> evenements = new ArrayList<>();

    public Long getIdTypeEvenement() {
        return idTypeEvenement;
    }

    public void setIdTypeEvenement(Long idTypeEvenement) {
        this.idTypeEvenement = idTypeEvenement;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

}