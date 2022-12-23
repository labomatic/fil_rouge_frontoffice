package com.fil_rouge_frontoffice.service;

import com.fil_rouge_frontoffice.controller.dto.SignupRequest;
import com.fil_rouge_frontoffice.controller.dto.UtilisateurDto;
import com.fil_rouge_frontoffice.entity.Role;
import com.fil_rouge_frontoffice.entity.StatutCompte;
import com.fil_rouge_frontoffice.entity.Utilisateur;
import com.fil_rouge_frontoffice.exception.CompteDejaExistantException;
import com.fil_rouge_frontoffice.repository.RoleRepository;
import com.fil_rouge_frontoffice.repository.StatutCompteRepository;
import com.fil_rouge_frontoffice.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private StatutCompteRepository statutCompteRepo;

    @Autowired
    private PasswordEncoder encoder;


    public UtilisateurDto findById(Long id) {
        Optional<Utilisateur> utilisateur = utilisateurRepo.findById(id);
        UtilisateurDto utilisateurDto = UtilisateurDto.from(utilisateur.orElse(null));
        return utilisateurDto;
    }

    public void signup(SignupRequest dto) throws CompteDejaExistantException {

        boolean utilisateurExiste = utilisateurRepo.existsByMail(dto.getMail());

        if(utilisateurExiste) {
            throw new CompteDejaExistantException();
        } else {
            Utilisateur utilisateur = new Utilisateur(dto.getNom(), dto.getPrenom(), dto.getMail(), encoder.encode(dto.getPassword()), dto.getVille(), dto.getPays(), setRole(), setStatutCompte());
            utilisateurRepo.save(utilisateur);
        }
    }

    public Role setRole() {
        return roleRepo.findRoleByIntitule("utilisateur");
    }

    public StatutCompte setStatutCompte() {
        return statutCompteRepo.findStatutCompteByIntitule("actif");
    }
}
