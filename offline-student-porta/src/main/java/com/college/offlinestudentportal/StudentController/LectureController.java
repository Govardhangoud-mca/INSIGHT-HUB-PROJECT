package com.college.offlinestudentportal.StudentController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.college.offlinestudentportal.entity.Lecture;
import com.college.offlinestudentportal.service.LectureService;

@RestController
@RequestMapping("/api/lectures")
@CrossOrigin(origins = "http://localhost:5173")
public class LectureController {

    @Autowired
    private LectureService lectureService;

    // Upload Lecture
    @PostMapping
    public ResponseEntity<Lecture> uploadLecture(@RequestBody Lecture lecture) {
        if (lecture.getVideoUrl() == null || lecture.getVideoUrl().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        Lecture savedLecture = lectureService.addLecture(lecture);
        return ResponseEntity.ok(savedLecture);
    }

    // Get List of Lectures (with optional subject & unit filter)
    @GetMapping
    public ResponseEntity<?> getLectures(
            @RequestParam(name = "subject", required = false) String subject,
            @RequestParam(name = "unit", required = false) String unit) {
        try {
            List<Lecture> lectures;

            if (subject != null && !subject.isEmpty() && unit != null && !unit.isEmpty()) {
                lectures = lectureService.getLecturesBySubjectAndUnit(subject, unit);
            } else if (subject != null && !subject.isEmpty()) {
                lectures = lectureService.getLecturesBySubject(subject);
            } else if (unit != null && !unit.isEmpty()) {
                lectures = lectureService.getLecturesByUnit(unit);
            } else {
                lectures = lectureService.getAllLectures();
            }

            return ResponseEntity.ok(lectures);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching lectures: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLecture(@PathVariable("id") Long id) {
        lectureService.deleteLecture(id);
        return ResponseEntity.noContent().build();
    }
}
