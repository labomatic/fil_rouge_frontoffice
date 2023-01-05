package com.fil_rouge_frontoffice.controller;

import com.fil_rouge_frontoffice.controller.dto.EvenementDto;

import com.fil_rouge_frontoffice.entity.Evenement;
import com.fil_rouge_frontoffice.entity.Utilisateur;
import com.fil_rouge_frontoffice.service.EvenementService;

import com.fil_rouge_frontoffice.service.UtilisateurService;
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
    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/add")
    public ResponseEntity<EvenementDto> addEvenement(@RequestBody EvenementDto dto){
        Utilisateur utilisateurConnecte = utilisateurService.getConnectedUtilisateur();
        if(!utilisateurConnecte.getMail().equals(dto.getMailUtilisateur())){
            if(!utilisateurService.isEcritureAutorisee(dto.getMailUtilisateur(), utilisateurConnecte.getMail())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        EvenementDto evenementDto = evenementService.addEvenement(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(evenementDto);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEvenement(@PathVariable("id") Long idEvenement){
        Optional<Evenement> optEvenement = evenementService.findById(idEvenement);
        if(optEvenement.isPresent()){
            Utilisateur utilisateurConnecte = utilisateurService.getConnectedUtilisateur();
            Utilisateur proprietaire = optEvenement.get().getUtilisateur();
            if(!utilisateurConnecte.getMail().equals(proprietaire.getMail())){
                if(!utilisateurService.isSuppressionAutorisee(proprietaire.getMail(), utilisateurConnecte.getMail())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            evenementService.delete(idEvenement);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatus> updateEvenement(@RequestBody EvenementDto evenement) {

        boolean exists = evenementService.existsById(evenement.getIdEvenement());
        if(exists) {
            Utilisateur utilisateurConnecte = utilisateurService.getConnectedUtilisateur();
            Utilisateur proprietaire = evenementService.findById(evenement.getIdEvenement()).get().getUtilisateur();
            if(!utilisateurConnecte.getMail().equals(proprietaire.getMail())){
                if(!utilisateurService.isModificationAutorisee(proprietaire.getMail(), utilisateurConnecte.getMail())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            evenementService.update(evenement);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvenementDto> fetchEvenementById(@PathVariable("id") Long id){
        Optional<Evenement> evenementOpt = evenementService.findById(id);
        if(evenementOpt.isPresent()){
            Utilisateur utilisateurConnecte = utilisateurService.getConnectedUtilisateur();
            Utilisateur proprietaire = evenementOpt.get().getUtilisateur();
            if(!utilisateurConnecte.getMail().equals(proprietaire.getMail())){
                if(!utilisateurService.isLectureAutorisee(proprietaire.getMail(), utilisateurConnecte.getMail())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            EvenementDto dto = EvenementDto.from(evenementOpt.get());
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @GetMapping("/proprietaire/{mail}")
    public ResponseEntity<List<EvenementDto>> fetchEvenementsByMail(@PathVariable("mail") String mail) {
        String mailUtilisateurConnecte = utilisateurService.getConnectedUtilisateur().getMail();
        if(!mailUtilisateurConnecte.equals(mail)){
            if(!utilisateurService.isLectureAutorisee(mail, mailUtilisateurConnecte)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<EvenementDto> listeEvenements = evenementService.findUpcomingEvenementsUtilisateur(mail);
        return ResponseEntity.status(HttpStatus.OK).body(listeEvenements);
    }

    @GetMapping("/planning/{mail}/{annee}/{mois}")
    public ResponseEntity<List<EvenementDto>> fetchEvenementsUtilisateurMois(@PathVariable String mail, @PathVariable int annee, @PathVariable int mois) {
        String mailUtilisateurConnecte = utilisateurService.getConnectedUtilisateur().getMail();
        if(!mailUtilisateurConnecte.equals(mail)){
            if(!utilisateurService.isLectureAutorisee(mail, mailUtilisateurConnecte)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<EvenementDto> listeEvenements = evenementService.findEvenementsUtilisateurMensuel(mail, annee, mois);
        return ResponseEntity.status(HttpStatus.OK).body(listeEvenements);
    }

}
