package com.israel.trupper.security;

import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;



@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private  String secret;
    @Value("${app.jwt.expiration-ms}")
    private  long expirationMs;


    public String generarToken(UserDetails userDetails){
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationMs);
        return Jwts.builder().subject(userDetails.getUsername()).claim("roles", roles).issuedAt(now).expiration(expiration).signWith(getKey()).compact();
    }


    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }


    public boolean isValidToken(String token, UserDetails userDetails){
        try {
            Claims claims = extractClaims(token);
            return claims.getSubject().equals(userDetails.getUsername()) && claims.getExpiration().after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            
            return false;
        }
    }

    private Claims extractClaims(String token){
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }

    public String extractName(String token){
        return extractClaims(token).getSubject();
    }


}
