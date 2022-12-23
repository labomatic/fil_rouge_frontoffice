package com.fil_rouge_frontoffice.security.jwt;

import com.fil_rouge_frontoffice.entity.Utilisateur;
import com.fil_rouge_frontoffice.repository.UtilisateurRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.utils.secret}")
    private String jwtSecret;
    @Value("${jwt.utils.expiration.duration}")
    private long expirationMs;

    public String generateJwt(Authentication authentication) {
        Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(utilisateur.getMail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + this.expirationMs))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
                    .build().parseClaimsJws(token);
            return true;
//        } catch (SignatureException e) {
//            System.err.println("Invalid JWT signature: {} " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.err.println("Invalid JWT token: {} " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.err.println("JWT token is expired: {} " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.err.println("JWT token is unsupported: {} " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("JWT claims string is empty: {} " + e.getMessage());
        }
        return false;
    }


    public String getMailFromToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
