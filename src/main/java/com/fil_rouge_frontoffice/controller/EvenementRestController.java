package com.fil_rouge_frontoffice.controller;

import com.fil_rouge_frontoffice.controller.dto.EvenementDto;

import com.fil_rouge_frontoffice.service.EvenementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/evenement")
public class EvenementRestController {
    @Autowired
    private EvenementService evenementService;

    @PostMapping("/add")
    public ResponseEntity<EvenementDto> addEvenement(@RequestBody EvenementDto dto){
        //Utilisateur utilisateur = utilisateurService.getConnectedUtilisateur();
        EvenementDto evenementDto = evenementService.addEvenement(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(evenementDto);
    }
    /*@PostMapping("/test")
    public ResponseEntity<TestDto> test(@RequestBody TestDto dto){
        System.out.println("test");
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }*/
}
