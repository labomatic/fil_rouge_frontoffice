package com.fil_rouge_frontoffice.service;

import com.fil_rouge_frontoffice.controller.dto.SignupRequest;
import com.fil_rouge_frontoffice.controller.dto.UtilisateurDto;
import com.fil_rouge_frontoffice.entity.Utilisateur;
import com.fil_rouge_frontoffice.exception.CompteDejaExistantException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;


@SpringBootTest
public class UtilisateurServiceTest {

    @Autowired
   UtilisateurService utilisateurService;


    //tests recherche utilisateur par email
    @Test
    void when_findByMail_notFound_throws_exception() {
        String mail = "mailinexistant";
        try {
            utilisateurService.findByMail(mail);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
        }
    }

    @Test
    void when_findByMail_OK_return_utilisateur() {
        String mail = "lisabordes@free.fr";
        UtilisateurDto utilisateur = utilisateurService.findByMail(mail);
        assertThat(utilisateur.getIdUtilisateur() == 14L);

    }

    //test signup avec une adresse mail déjà liée à un compte
    @Test
    void when_signup_compte_dejaExistant_throws_exception() {

        SignupRequest compteTest = new SignupRequest();
        compteTest.setMail("lisabordes@free.fr");
        try {
            utilisateurService.signup(compteTest);
            fail("CompteDejaExistantException expected");
        } catch (CompteDejaExistantException e) {

        }
    }

}
