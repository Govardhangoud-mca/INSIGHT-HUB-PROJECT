package com.college.offlinestudentportal.StudentRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.college.offlinestudentportal.entity.StudyMaterial;

public interface StudyMaterialRepository extends JpaRepository<StudyMaterial, Long> {
    
    // Custom query to search study materials by title (ignoring case)
    List<StudyMaterial> findByTitleContainingIgnoreCase(String title);
}
