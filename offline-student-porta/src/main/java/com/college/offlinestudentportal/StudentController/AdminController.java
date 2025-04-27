package com.college.offlinestudentportal.StudentController;




import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")  // Fixed the path
public class AdminController {
    @GetMapping("hello")
    public Map<String, String> getAdminDashboard() {
        return Map.of("message", "Welcome to the Admin Dashboard");
    }
}
