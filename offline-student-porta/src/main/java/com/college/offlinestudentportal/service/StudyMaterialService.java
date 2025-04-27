package com.college.offlinestudentportal.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.offlinestudentportal.StudentRepository.StudyMaterialRepository;
import com.college.offlinestudentportal.entity.StudyMaterial;

@Service
public class StudyMaterialService {

    @Autowired
    private StudyMaterialRepository repository;

    // Get all study materials
    public List<StudyMaterial> getAllMaterials() {
        return repository.findAll();
    }

    // Save new study material
    public StudyMaterial saveMaterial(StudyMaterial material) {
        return repository.save(material);
    }

    // Get study material by ID
    public Optional<StudyMaterial> getMaterialById(Long id) {
        return repository.findById(id);
    }

    // Search study materials by title
    public List<StudyMaterial> searchMaterials(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }

    // Update existing study material
    public StudyMaterial updateMaterial(Long id, StudyMaterial updatedMaterial) {
        return repository.findById(id).map(material -> {
            material.setTitle(updatedMaterial.getTitle());
            material.setSubject(updatedMaterial.getSubject());
            material.setType(updatedMaterial.getType());
            material.setSize(updatedMaterial.getSize());
            material.setPages(updatedMaterial.getPages());
            material.setAuthor(updatedMaterial.getAuthor());
            material.setLastUpdated(updatedMaterial.getLastUpdated());
            material.setDownloadUrl(updatedMaterial.getDownloadUrl());
            return repository.save(material);
        }).orElseThrow(() -> new RuntimeException("Study Material not found with id: " + id));
    }

    // Delete study material by ID
    public void deleteMaterial(Long id) {
        repository.deleteById(id);
    }
}
