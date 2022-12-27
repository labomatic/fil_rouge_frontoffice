package com.fil_rouge_frontoffice.controller;

import com.fil_rouge_frontoffice.controller.dto.UtilisateurDto;
import com.fil_rouge_frontoffice.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
