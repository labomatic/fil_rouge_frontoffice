package com.fil_rouge_frontoffice.controller;

import com.fil_rouge_frontoffice.controller.dto.AvoirDroitsCrudPlanningAutreUtilisateurDto;
import com.fil_rouge_frontoffice.controller.dto.RequeteDroitsDto;
import com.fil_rouge_frontoffice.controller.dto.SignupRequest;
import com.fil_rouge_frontoffice.controller.dto.UtilisateurDto;
import com.fil_rouge_frontoffice.entity.AvoirDroitsCrudPlanningAutreUtilisateur;
import com.fil_rouge_frontoffice.entity.Utilisateur;
import com.fil_rouge_frontoffice.exception.UtilisateurNotFoundException;
import com.fil_rouge_frontoffice.repository.UtilisateurRepository;
import com.fil_rouge_frontoffice.service.AvoirDroitsCrudPlanningAutreUtilisateurService;
import com.fil_rouge_frontoffice.service.UtilisateurService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class UtilisateurRestController {

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private AvoirDroitsCrudPlanningAutreUtilisateurService adService;

    @Autowired
    private UtilisateurRepository utilisateurRepo;

    @GetMapping("/profil/{id}")
    public ResponseEntity<UtilisateurDto> fetchUtilisateurById(@PathVariable("id") Long id) {
        UtilisateurDto utilisateur = utilisateurService.findById(id);
        if(utilisateur == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(utilisateur);
        }
    }

    @GetMapping("/utilisateur/{mail}")
    public ResponseEntity<UtilisateurDto> fetchUtilisateurByMail(@PathVariable("mail") String mail) {
        UtilisateurDto utilisateur = utilisateurService.findByMail(mail);
        if(utilisateur == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(utilisateur);
        }
    }

    @GetMapping("/recherche/{string}")
    public ResponseEntity<List<UtilisateurDto>> searchUtilisateurs(@PathVariable("string") String string) {
        List<UtilisateurDto> utilisateurs = utilisateurService.searchUtilisateurs(string);
        if(utilisateurs.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(utilisateurs);
        }
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = utilisateurService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PutMapping("/profil")
    public ResponseEntity<?> updateProfile(@RequestParam Long idUtilisateur, @RequestParam String nom, @RequestParam String prenom,
                                           @RequestParam String ville,
                                           @RequestParam String pays,
                                           @RequestParam MultipartFile photo){
        Utilisateur utilisateurConnecte = utilisateurService.getConnectedUtilisateur();
        if(utilisateurConnecte.getIdUtilisateur() != idUtilisateur){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        SignupRequest dto = new SignupRequest(idUtilisateur, prenom, ville, pays, photo , nom);
        dto.setMail(utilisateurConnecte.getMail());
        if(utilisateurService.updateProfil(dto)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PutMapping("/profil-garder-photo")
    public ResponseEntity<?> updateProfileKeepPhoto(@RequestParam Long idUtilisateur, @RequestParam String nom, @RequestParam String prenom,
                                           @RequestParam String ville,
                                           @RequestParam String pays
                                          ){
        Utilisateur utilisateurConnecte = utilisateurService.getConnectedUtilisateur();
        if(utilisateurConnecte.getIdUtilisateur() != idUtilisateur){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        SignupRequest dto = new SignupRequest(idUtilisateur, prenom, ville, pays, nom);
        dto.setMail(utilisateurConnecte.getMail());
        if(utilisateurService.updateProfil(dto)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PostMapping("/droits")
    public ResponseEntity<AvoirDroitsCrudPlanningAutreUtilisateurDto> getDroits(@RequestBody RequeteDroitsDto dto)   {
        String proprietaire = dto.getMailProprietaire();
        String ayantDroit = dto.getMailAyantDroit();
        Utilisateur utilisateurConnecte = utilisateurService.getConnectedUtilisateur();
        if(!(utilisateurConnecte.getMail().equals(proprietaire) || utilisateurConnecte.getMail().equals(ayantDroit))){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        AvoirDroitsCrudPlanningAutreUtilisateurDto droits = utilisateurService.getDroits(proprietaire, ayantDroit);
        return ResponseEntity.status(HttpStatus.OK).body(droits);
    }

    @PutMapping("/droits")
    public ResponseEntity<?> updateDroits(@RequestBody AvoirDroitsCrudPlanningAutreUtilisateurDto dto){
        String mailProprietaire = dto.getMailProprietaire();
        String mailAyantDroit = dto.getMailAyantDroit();
        Utilisateur utilisateurConnecte = utilisateurService.getConnectedUtilisateur();
        if(utilisateurConnecte == null || !utilisateurConnecte.getMail().equals(mailProprietaire)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Utilisateur ayantDroit = utilisateurService.findUtilisateurByMail(mailAyantDroit);
        if(ayantDroit == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        AvoirDroitsCrudPlanningAutreUtilisateur ad = new AvoirDroitsCrudPlanningAutreUtilisateur(utilisateurConnecte, ayantDroit, dto.isPeutLire(), dto.isPeutEcrire(), dto.isPeutModifier(), dto.isPeutSupprimer());
        adService.saveOrUpdate(ad);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/utilisateur/{id}")
    public ResponseEntity<HttpStatus> deleteUtilisateur(@PathVariable("id") Long id) {
        try {
            Utilisateur utilisateurConnecte = utilisateurService.getConnectedUtilisateur();
            if(utilisateurConnecte == null || !utilisateurConnecte.getIdUtilisateur().equals(id)){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            Optional<Utilisateur> utilisateur = utilisateurRepo.findById(id);
            if(utilisateur.isEmpty()) {
                throw new UtilisateurNotFoundException();
            }

            adService.supprimerRelations(utilisateur.get());
            utilisateurService.deleteUtilisateurById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (UtilisateurNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
