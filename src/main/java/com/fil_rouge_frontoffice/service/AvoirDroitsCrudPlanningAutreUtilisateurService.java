package com.fil_rouge_frontoffice.service;

import com.fil_rouge_frontoffice.controller.dto.AvoirDroitsCrudPlanningAutreUtilisateurDto;
import com.fil_rouge_frontoffice.entity.AvoirDroitsCrudPlanningAutreUtilisateur;
import com.fil_rouge_frontoffice.entity.ClePartagePlanning;
import com.fil_rouge_frontoffice.repository.AvoirDroitsCrudPlanningAutreUtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvoirDroitsCrudPlanningAutreUtilisateurService {
    @Autowired
    private AvoirDroitsCrudPlanningAutreUtilisateurRepository adRepository;
    @Autowired
    private UtilisateurService utilisateurService;

    public void saveOrUpdate(AvoirDroitsCrudPlanningAutreUtilisateur ad){
        adRepository.save(ad);
    }
    public boolean isLectureAutorisee(String mailProprietaire, String mailAutre){
        ClePartagePlanning cle = new ClePartagePlanning(utilisateurService.findUtilisateurByMail(mailProprietaire).getIdUtilisateur(), utilisateurService.findUtilisateurByMail(mailAutre).getIdUtilisateur());
        Optional<AvoirDroitsCrudPlanningAutreUtilisateur> droits = adRepository.findById(cle);
        return droits.isPresent() && droits.get().getPeutLire();
    }
    public boolean isEcritureAutorisee(String mailProprietaire, String mailAutre){
        ClePartagePlanning cle = new ClePartagePlanning(utilisateurService.findUtilisateurByMail(mailProprietaire).getIdUtilisateur(), utilisateurService.findUtilisateurByMail(mailAutre).getIdUtilisateur());
        Optional<AvoirDroitsCrudPlanningAutreUtilisateur> droits = adRepository.findById(cle);
        return droits.isPresent() && droits.get().getPeutCreer();
    }
    public boolean isModificationAutorisee(String mailProprietaire, String mailAutre){
        ClePartagePlanning cle = new ClePartagePlanning(utilisateurService.findUtilisateurByMail(mailProprietaire).getIdUtilisateur(), utilisateurService.findUtilisateurByMail(mailAutre).getIdUtilisateur());
        Optional<AvoirDroitsCrudPlanningAutreUtilisateur> droits = adRepository.findById(cle);
        return droits.isPresent() && droits.get().getPeutModifier();
    }
    public boolean isSuppressionAutorisee(String mailProprietaire, String mailAutre){
        ClePartagePlanning cle = new ClePartagePlanning(utilisateurService.findUtilisateurByMail(mailProprietaire).getIdUtilisateur(), utilisateurService.findUtilisateurByMail(mailAutre).getIdUtilisateur());
        Optional<AvoirDroitsCrudPlanningAutreUtilisateur> droits = adRepository.findById(cle);
        return droits.isPresent() && droits.get().getPeutSupprimer();
    }
}
