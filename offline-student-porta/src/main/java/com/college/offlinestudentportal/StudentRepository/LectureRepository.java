package com.college.offlinestudentportal.StudentRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.college.offlinestudentportal.entity.Lecture;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByTitleContainingIgnoreCase(String title);
    List<Lecture> findBySubjectIgnoreCase(String subject);
    List<Lecture> findByUnit(String unit);  // New method for unit filtering
    List<Lecture> findBySubjectIgnoreCaseAndUnit(String subject, String unit);  // New method for subject + unit
}
