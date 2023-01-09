package com.fil_rouge_frontoffice.exception;

public class UtilisateurNotFoundException extends Exception{
    public UtilisateurNotFoundException() {
        super("Utilisateur introuvable");
    }
}
