package com.exercise.uno.models.entity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
@Deprecated
public class JWtTokenUtil {

    private static final String SECRET_KEY = "secret_key";
    public static final long EXPIRATION_TIME = 86400000L;

    //Metodo per generare il token
    public static String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // Metodo per ottenere il nome utente dal token
    public static String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    // Metodo per ottenere le informazioni del token
    private static Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // Metodo per controllare se il token è scaduto
    private static boolean isTokenExpired(String token) {
        return getClaimsFromToken(token).getExpiration().before(new Date());
    }

    // Metodo per validare il token
    public static boolean validateToken(String token, String username) {
        return (username.equals(getUsernameFromToken(token)) && !isTokenExpired(token));
    }
}

