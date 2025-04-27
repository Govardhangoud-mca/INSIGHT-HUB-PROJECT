package com.college.offlinestudentportal.StudentController;

import com.college.offlinestudentportal.dto.SubjectDto;
import com.college.offlinestudentportal.entity.Subject;
import com.college.offlinestudentportal.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty/subjects")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/add")
    public ResponseEntity<Subject> addSubject(@RequestBody SubjectDto subjectDto) {
        Subject savedSubject = subjectService.addSubject(subjectDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSubject);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/by-department-semester")
    public ResponseEntity<List<Subject>> getSubjectsByDepartmentAndSemester(
            @RequestParam("department") String department,
            @RequestParam("semester") int semester) {
        List<Subject> subjects = subjectService.getSubjectsByDepartmentAndSemester(department, semester);
        return ResponseEntity.ok(subjects);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable("id") Long id, @RequestBody SubjectDto subjectDto) {
        Subject updatedSubject = subjectService.updateSubject(id, subjectDto);
        return ResponseEntity.ok(updatedSubject);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable("id") Long id) {
        boolean deleted = subjectService.deleteSubject(id);
        return deleted ? ResponseEntity.ok("Subject deleted successfully") : ResponseEntity.notFound().build();
    }
}
