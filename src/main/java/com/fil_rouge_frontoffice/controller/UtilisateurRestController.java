package com.fil_rouge_frontoffice.controller;

import com.fil_rouge_frontoffice.controller.dto.SignupRequest;
import com.fil_rouge_frontoffice.controller.dto.UtilisateurDto;
import com.fil_rouge_frontoffice.entity.Utilisateur;
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

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class UtilisateurRestController {

    @Autowired
    private UtilisateurService utilisateurService;

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

}
