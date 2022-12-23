package com.fil_rouge_frontoffice.exception;

public class CompteDejaExistantException extends Exception {

    public CompteDejaExistantException() {
        super("Un compte existe déjà avec cette adresse email");
    }
}
