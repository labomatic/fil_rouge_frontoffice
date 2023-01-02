package com.fil_rouge_frontoffice.controller;

import com.fil_rouge_frontoffice.controller.dto.EvenementDto;

import com.fil_rouge_frontoffice.entity.Evenement;
import com.fil_rouge_frontoffice.service.EvenementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEvenement(@PathVariable("id") Long idEvenement){
        Optional<Evenement> optEvenement = evenementService.findById(idEvenement);
        if(optEvenement.isPresent()){
            evenementService.delete(idEvenement);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatus> updateEvenement(@RequestBody EvenementDto evenement) {

        boolean exists = evenementService.update(evenement);
        if(exists) return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvenementDto> fetchEvenementById(@PathVariable("id") Long id){
        Optional<Evenement> evenementOpt = evenementService.findById(id);
        if(evenementOpt.isPresent()){
            EvenementDto dto = EvenementDto.from(evenementOpt.get());
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @GetMapping("/proprietaire/{mail}")
    public ResponseEntity<List<EvenementDto>> fetchEvenementsByMail(@PathVariable("mail") String mail) {
        List<EvenementDto> listeEvenements = evenementService.findUpcomingEvenementsUtilisateur(mail);
        return ResponseEntity.status(HttpStatus.OK).body(listeEvenements);
    }
}
