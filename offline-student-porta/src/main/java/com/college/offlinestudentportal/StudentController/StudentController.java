package com.college.offlinestudentportal.StudentController;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/dashboard")
    public Map<String, String> getStudentDashboard() {
        return Map.of("message", "Welcome to the Student Dashboard");
    }
}
