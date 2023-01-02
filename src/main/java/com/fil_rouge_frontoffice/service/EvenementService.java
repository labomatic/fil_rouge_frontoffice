package com.fil_rouge_frontoffice.service;

import com.fil_rouge_frontoffice.controller.dto.EvenementDto;
import com.fil_rouge_frontoffice.entity.Evenement;
import com.fil_rouge_frontoffice.entity.TypeEvenement;
import com.fil_rouge_frontoffice.repository.EvenementRepository;
import com.fil_rouge_frontoffice.repository.TypeEvenementRepository;
import com.fil_rouge_frontoffice.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EvenementService {
    @Autowired
    private EvenementRepository evenementRepository;

    @Autowired
    private TypeEvenementRepository typeEvenementRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public EvenementDto addEvenement(EvenementDto dto) {
        Evenement evenement = dto.toEvenement();
        evenement.setUtilisateur(utilisateurRepository.findUtilisateurByMail(dto.getMailUtilisateur()).get());
        // On attribue un type générique (évolutif pour versions suivantes)
        evenement.setTypeEvenement(getTypeGenerique());
        evenementRepository.save(evenement);
        return EvenementDto.from(evenement);
    }

    private TypeEvenement getTypeGenerique(){
        return typeEvenementRepository.findTypeEvenementByIntitule("generique");
    }
}
