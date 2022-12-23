package com.fil_rouge_frontoffice.security.jwt;

public class JwtResponse {

    private String mail;

    private String token;

    public JwtResponse() {
    }

    public JwtResponse(String mail, String generatedToken) {
        this.mail = mail;
        this.token = generatedToken;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
