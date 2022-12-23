package com.fil_rouge_frontoffice.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "utilisateur", schema = "plannings_meteo")
public class Utilisateur implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_utilisateur", nullable = false)
    private Long idUtilisateur;

    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;

    @Column(name = "mail", nullable = false, length = 50, unique = true)
    private String mail;

    @Column(name = "ville", nullable = true, length = 50)
    private String ville;

    @Column(name = "pays", nullable = true, length = 50)
    private String pays;

    @Column(name = "photo", nullable = true, length = 50)
    private String photo;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Column(name = "mot_de_passe", nullable = false, length = 250)
    private String motDePasse;

    @ManyToOne
    @JoinColumn(name = "id_statut_compte")
    private StatutCompte statutCompte;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    @OneToMany (targetEntity = Evenement.class, mappedBy = "utilisateur", cascade = CascadeType.REMOVE)
    private List<Evenement> planning = new ArrayList<>();
    public Utilisateur(){

    }
    public Utilisateur(String prenom, String nom, String motDePasse, StatutCompte statutCompte, Role role) {
        this.prenom = prenom;
        this.nom = nom;
        this.motDePasse = motDePasse;
        this.statutCompte = statutCompte;
        this.role = role;
        this.mail = "";
        this.pays = "";
        this.ville = "";
        this.planning = new ArrayList<>();
        this.photo = "";
    }

    public Utilisateur(Long id, String nom, String prenom, String mail, String motDePasse,String ville, String pays, Role role, StatutCompte statutCompte) {
        this.idUtilisateur = id;
        this.prenom = prenom;
        this.mail = mail;
        this.ville = ville;
        this.pays = pays;
        this.nom = nom;
        this.motDePasse = motDePasse;
        this.statutCompte = statutCompte;
        this.role = role;
    }

    public Utilisateur(String nom, String prenom, String mail, String motDePasse,String ville, String pays, Role role, StatutCompte statutCompte) {
        this.prenom = prenom;
        this.mail = mail;
        this.ville = ville;
        this.pays = pays;
        this.nom = nom;
        this.motDePasse = motDePasse;
        this.statutCompte = statutCompte;
        this.role = role;
    }

    public List<Evenement> getPlanning() {
        return planning;
    }

    public void setPlanning(List<Evenement> planning) {
        this.planning = planning;
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

    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        return null;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public StatutCompte getStatutCompte() {
        return statutCompte;
    }

    public void setStatutCompte(StatutCompte statutCompte) {
        this.statutCompte = statutCompte;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public boolean isActive(){
        return this.statutCompte.getIntitule().equals("actif");
    }
    public boolean isSuperAdmin(){
        return this.role.getIntitule().equals("superAdmin");
    }
    public boolean isBasicUser(){
        return this.role.getIntitule().equals("utilisateur");
    }
    public boolean isBasicAdmin(){
        return this.role.getIntitule().equals("admin");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getIntitule()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}