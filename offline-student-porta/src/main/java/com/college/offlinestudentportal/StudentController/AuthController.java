package com.college.offlinestudentportal.StudentController;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import com.college.offlinestudentportal.StudentRepository.UserRepository;
import com.college.offlinestudentportal.entity.User;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:5173")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        }

        if (user.getRole() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role is required");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser, HttpSession session) {
        Optional<User> userOptional = userRepository.findByEmail(loginUser.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(loginUser.getPassword(), user.getPassword())) {
                
                // Create authentication object with roles
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        user.getEmail(), null, Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
                );

                // Store authentication in SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);
                session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

                // Save user in session
                session.setAttribute("user", user);

                // Debugging
                System.out.println("✅ User logged in: " + user.getEmail() + " | Role: " + user.getRole());
                System.out.println("✅ Session ID: " + session.getId());

                // JSON Response
                return ResponseEntity.ok().body(
                        "{\"message\": \"Login successful\", \"email\": \"" + user.getEmail() + "\", \"role\": \"" + user.getRole() + "\", \"sessionId\": \"" + session.getId() + "\"}"
                );
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Invalid credentials\"}");
    }

    @GetMapping("/session")
    public ResponseEntity<?> checkSession(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            System.out.println("✅ Session User: " + user.getEmail() + " | Role: " + user.getRole());
            return ResponseEntity.ok().body(
                    "{\"message\": \"Session active\", \"email\": \"" + user.getEmail() + "\", \"role\": \"" + user.getRole() + "\"}"
            );
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Session expired\"}");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("{\"message\": \"Logged out successfully\"}");
    }
}
