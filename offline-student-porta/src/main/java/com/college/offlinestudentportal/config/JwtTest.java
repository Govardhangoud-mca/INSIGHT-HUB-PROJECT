package com.college.offlinestudentportal.config;

import com.college.offlinestudentportal.service.JwtService;

public class JwtTest {
    public static void main(String[] args) {
        JwtService jwtService = new JwtService();
        
        // Generate Token
        String token = jwtService.generateToken("testuser");
        System.out.println("Generated Token: " + token);

        // Validate Token
        boolean isValid = jwtService.isTokenValid(token, "testuser");
        System.out.println("Is Token Valid: " + isValid);

        // Extract Username
        String username = jwtService.extractUsername(token);
        System.out.println("Extracted Username: " + username);
    }
}
