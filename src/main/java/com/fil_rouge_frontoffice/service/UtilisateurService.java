package com.fil_rouge_frontoffice.service;

import com.fil_rouge_frontoffice.controller.dto.UtilisateurDto;
import com.fil_rouge_frontoffice.entity.Utilisateur;
import com.fil_rouge_frontoffice.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepo;


    public UtilisateurDto findById(Long id) {
        Optional<Utilisateur> utilisateur = utilisateurRepo.findById(id);
        UtilisateurDto utilisateurDto = UtilisateurDto.from(utilisateur.orElse(null));
        return utilisateurDto;
    }
}
