package com.college.offlinestudentportal.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.college.offlinestudentportal.StudentRepository.LectureRepository;
import com.college.offlinestudentportal.entity.Lecture;

@Service
public class LectureService {

    @Autowired
    private LectureRepository lectureRepository;

    public Lecture addLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

    public List<Lecture> getLecturesBySubject(String subject) {
        return lectureRepository.findBySubjectIgnoreCase(subject);
    }

    public List<Lecture> getLecturesByUnit(String unit) {  // New method
        return lectureRepository.findByUnit(unit);
    }

    public List<Lecture> getLecturesBySubjectAndUnit(String subject, String unit) {  // New method
        return lectureRepository.findBySubjectIgnoreCaseAndUnit(subject, unit);
    }

    public void deleteLecture(Long id) {
        lectureRepository.deleteById(id);
    }
}
