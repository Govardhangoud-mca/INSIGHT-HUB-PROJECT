package com.college.offlinestudentportal.config;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.secret}") 
    private String secretKey;

    private SecretKey key;

    @PostConstruct
    public void init() {
        try {
            // Decode the Base64 secret key
            byte[] keyBytes = Decoders.BASE64.decode(secretKey);
            key = Keys.hmacShaKeyFor(keyBytes);
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT Secret Key: " + e.getMessage(), e);
        }
    }

    public SecretKey getKey() {
        return key;
    }
}

