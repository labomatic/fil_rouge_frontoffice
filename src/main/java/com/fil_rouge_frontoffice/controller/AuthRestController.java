package com.fil_rouge_frontoffice.controller;

import com.fil_rouge_frontoffice.controller.dto.SigninRequest;
import com.fil_rouge_frontoffice.controller.dto.SignupRequest;
import com.fil_rouge_frontoffice.entity.Utilisateur;
import com.fil_rouge_frontoffice.exception.CompteDejaExistantException;
import com.fil_rouge_frontoffice.security.jwt.JwtResponse;
import com.fil_rouge_frontoffice.security.jwt.JwtUtils;
import com.fil_rouge_frontoffice.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestParam String nom, @RequestParam String prenom,
                                    @RequestParam String mail, @RequestParam String ville,
                                    @RequestParam String pays, @RequestParam String password,
                                    @RequestParam MultipartFile photo) {
        SignupRequest dto = new SignupRequest(mail, password, prenom, nom, ville, pays, photo);
        try{
            utilisateurService.signup(dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (CompteDejaExistantException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest dto) throws Exception{
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(dto.getMail(), dto.getPassword());

        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(auth);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String generatedToken = jwtUtils.generateJwt(authentication);

            Utilisateur connectedUser = (Utilisateur) authentication.getPrincipal();
            JwtResponse jwtResponse = new JwtResponse(connectedUser.getMail(), generatedToken);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(jwtResponse);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
