package com.example.banking_system.Bank;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

private final Key SECRET = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

    public String generateToken(String username){
        return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 86400000))
        .signWith(SECRET)
        .compact();
    }
    public String extraUsername(String token){
        return Jwts.parserBuilder()
        .setSigningKey(SECRET)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
    }
}
