package com.college.offlinestudentportal.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    // ✅ Password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ Store authentication in session (SecurityContext)
    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    // ✅ Security filter chain configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs (make sure frontend handles this)
            .authorizeHttpRequests(auth -> auth

                // ✅ Public endpoints (accessible without authentication)
                .requestMatchers(
                    "/api/auth/register",
                    "/api/auth/login",
                    "/api/auth/session",
                    "/api/debug",
                    "/api/resource-files/**",
                    "/api/faculty/subjects/**",
                    "/api/lectures/**",
                    "/api/resources/**"
                ).permitAll()

                // ✅ Student endpoints (must be logged in with STUDENT role)
                .requestMatchers("/api/student/**").hasAuthority("STUDENT")

                // ✅ Faculty-specific POST for adding subjects
                .requestMatchers(HttpMethod.POST, "/api/faculty/subjects/add").hasAuthority("FACULTY")

                // ✅ PUT for subject updates
                .requestMatchers(HttpMethod.PUT, "/api/faculty/subjects/update/**").hasAuthority("FACULTY")

                // ✅ Resource upload (Faculty only)
                .requestMatchers(HttpMethod.POST, "/api/resource-files/upload").hasAuthority("FACULTY")

                // ✅ All Faculty-related routes
                .requestMatchers("/api/faculty/**").hasAuthority("FACULTY")

                // ✅ Admin-only routes
                .requestMatchers("/api/admin/**").hasAuthority("ADMIN")

                // ✅ Any other request must be authenticated
                .anyRequest().authenticated()
            )
            .securityContext(securityContext -> securityContext
                .securityContextRepository(securityContextRepository())
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Use session-based auth
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/auth/logout"))
                .addLogoutHandler(customLogoutHandler())
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("{\"message\": \"Logged out successfully\"}");
                    response.setStatus(200);
                })
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
            );

        return http.build();
    }

    // ✅ Custom logout handler
    @Bean
    public LogoutHandler customLogoutHandler() {
        return (request, response, authentication) -> {
            HttpSession session = request.getSession(false);
            if (session != null) {
                System.out.println("✅ Session invalidated on logout.");
                session.invalidate();
            } else {
                System.out.println("❌ No session found to invalidate.");
            }
        };
    }
}
