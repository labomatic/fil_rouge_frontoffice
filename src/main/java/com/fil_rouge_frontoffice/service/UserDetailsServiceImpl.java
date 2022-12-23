package com.fil_rouge_frontoffice.service;

import com.fil_rouge_frontoffice.entity.Utilisateur;
import com.fil_rouge_frontoffice.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails utilisateur = utilisateurRepo.findUtilisateurByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException("username " + username + " not found"));
        return utilisateur;
    }
}
