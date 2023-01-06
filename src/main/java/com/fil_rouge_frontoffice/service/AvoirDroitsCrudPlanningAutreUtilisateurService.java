package com.fil_rouge_frontoffice.service;

import com.fil_rouge_frontoffice.controller.dto.AvoirDroitsCrudPlanningAutreUtilisateurDto;
import com.fil_rouge_frontoffice.entity.AvoirDroitsCrudPlanningAutreUtilisateur;
import com.fil_rouge_frontoffice.entity.ClePartagePlanning;
import com.fil_rouge_frontoffice.repository.AvoirDroitsCrudPlanningAutreUtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvoirDroitsCrudPlanningAutreUtilisateurService {
    @Autowired
    private AvoirDroitsCrudPlanningAutreUtilisateurRepository adRepository;

    /*public void updateDroits(AvoirDroitsCrudPlanningAutreUtilisateur ad){
        ClePartagePlanning cle = new ClePartagePlanning(ad.getProprietaire(), ad.getAyantDroit());
        boolean droitsExiste = adRepository.existsById(cle);
        if(droitsExiste){
            adRepository.save(ad);
        }
    }
    public void save(AvoirDroitsCrudPlanningAutreUtilisateur ad){
        ClePartagePlanning cle = new ClePartagePlanning(ad.getProprietaire(), ad.getAyantDroit());
        boolean droitsExiste = adRepository.existsById(cle);
        if(!droitsExiste){
            adRepository.save(ad);
        }
    }*/
    public void saveOrUpdate(AvoirDroitsCrudPlanningAutreUtilisateur ad){
        adRepository.save(ad);
    }
}
