package com.fil_rouge_frontoffice.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role", schema = "plannings_meteo")
public class Role {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_role", nullable = false)
    private Long idRole;

    @Column(name = "intitule", unique = true, nullable = false, length = 20)
    private String intitule;

    @OneToMany (targetEntity = Utilisateur.class, mappedBy = "role")
    private List<Utilisateur> utilisateurs = new ArrayList<>();

    public Role() {
    }

    public Role(String intitule) {
        this.intitule = intitule;
        this.utilisateurs = new ArrayList<>();
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

}
