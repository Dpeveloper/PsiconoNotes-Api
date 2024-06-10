package com.devcorp.psiconote.Security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {
    private @Value("${jwt-secret}") String secret;
    private @Value("${jwt-expiration}") Long expiration;

    public String generateToken(String username){
        return Jwts.builder().setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256).compact();
    }

    public Key getSignatureKey(){
        byte[] keyBytes= Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token).getBody();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims claims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token).getBody();
    }

    public <T> T claimEspecifico(String token, Function<Claims,T> claimsTFunction){
        Claims claims=claims(token);
        return claimsTFunction.apply(claims);
    }

    public String getUsername(String token){
        return claimEspecifico(token,Claims::getSubject);
    }
}
