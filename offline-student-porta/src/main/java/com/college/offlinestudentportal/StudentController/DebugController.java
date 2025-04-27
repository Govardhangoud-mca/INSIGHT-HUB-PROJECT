package com.college.offlinestudentportal.StudentController;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugController {

    @GetMapping("/api/debug")
    public String checkUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return "❌ No authentication found!";
        }

        StringBuilder roles = new StringBuilder("✅ Authenticated User: " + authentication.getName() + " | Roles: ");
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            roles.append(authority.getAuthority()).append(" ");
        }
        return roles.toString();
    }
}
