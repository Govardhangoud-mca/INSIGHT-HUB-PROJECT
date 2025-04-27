package com.college.offlinestudentportal.StudentController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.college.offlinestudentportal.StudentRepository.StudyMaterialRepository;
import com.college.offlinestudentportal.entity.StudyMaterial;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/study-materials")
@CrossOrigin("*")
public class StudyMaterialController {

	@Autowired
	private StudyMaterialRepository studyMaterialRepository;


    // 1. Get All Study Materials
    @GetMapping
    public List<StudyMaterial> getAllStudyMaterials() {
        return studyMaterialRepository.findAll();
    }

    // 2. Get Study Material by ID
    @GetMapping("/{id}")
    public ResponseEntity<StudyMaterial> getStudyMaterialById(@PathVariable Long id) {
        Optional<StudyMaterial> studyMaterial = studyMaterialRepository.findById(id);
        return studyMaterial.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Add New Study Material
    @PostMapping
    public StudyMaterial createStudyMaterial(@RequestBody StudyMaterial studyMaterial) {
        return studyMaterialRepository.save(studyMaterial);
    }

    // 4. Update Study Material
    @PutMapping("/{id}")
    public ResponseEntity<StudyMaterial> updateStudyMaterial(@PathVariable("id") Long id, 
                                                             @RequestBody StudyMaterial updatedStudyMaterial) {
        return studyMaterialRepository.findById(id).map(studyMaterial -> {
            studyMaterial.setTitle(updatedStudyMaterial.getTitle());
            studyMaterial.setSubject(updatedStudyMaterial.getSubject());
            studyMaterial.setType(updatedStudyMaterial.getType());
            studyMaterial.setSize(updatedStudyMaterial.getSize());
            studyMaterial.setPages(updatedStudyMaterial.getPages());
            studyMaterial.setAuthor(updatedStudyMaterial.getAuthor());
            studyMaterial.setLastUpdated(updatedStudyMaterial.getLastUpdated());
            studyMaterial.setDownloadUrl(updatedStudyMaterial.getDownloadUrl());
            return ResponseEntity.ok(studyMaterialRepository.save(studyMaterial));
        }).orElse(ResponseEntity.notFound().build());
    }


    // 5. Delete Study Material
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudyMaterial(@PathVariable(name = "id") Long id) {
        Optional<StudyMaterial> studyMaterial = studyMaterialRepository.findById(id);
        if (studyMaterial.isPresent()) {
            studyMaterialRepository.delete(studyMaterial.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

