package com.restaurante.tech.application.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final long expiresIn = 3600L;

    public String generateToken(String username, String scope) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(username)
                .claim("scope", scope)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expiresIn * 1000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public long getExpiresInSeconds() {
        return expiresIn;
    }
}
