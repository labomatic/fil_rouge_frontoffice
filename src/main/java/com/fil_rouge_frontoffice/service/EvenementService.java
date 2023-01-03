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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Optional<Evenement> findById(Long id){
        return evenementRepository.findById(id);
    }
    public boolean existsById(Long id){
        return evenementRepository.existsByIdEvenement(id);
    }

    private TypeEvenement getTypeGenerique(){
        return typeEvenementRepository.findTypeEvenementByIntitule("generique");
    }

    public void delete(Long idEvenement) {
        evenementRepository.deleteById(idEvenement);
    }
    public boolean update(EvenementDto dto){
        if(evenementRepository.existsByIdEvenement(dto.getIdEvenement())){
            Evenement evenement = dto.toEvenement();
            evenement.setTypeEvenement(getTypeGenerique());
            evenement.setUtilisateur(utilisateurRepository.findUtilisateurByMail(dto.getMailUtilisateur()).get());
            evenementRepository.save(evenement);
            return true;
        }
        return false;
    }

    public List<EvenementDto> findUpcomingEvenementsUtilisateur(String mail) {
        LocalDateTime now = LocalDateTime.now();
        return evenementRepository.findUpcomingEvenementsUtilisateur(mail, now)
                .stream()
                .map(evenement -> EvenementDto.from(evenement))
                .collect(Collectors.toList());
    }
}
