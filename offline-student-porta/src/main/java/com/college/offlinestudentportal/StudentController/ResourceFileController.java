package com.college.offlinestudentportal.StudentController;

import com.college.offlinestudentportal.entity.ResourceFile;
import com.college.offlinestudentportal.service.ResourceFileService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/resource-files") // ✅ Updated Base Path to avoid conflicts
@CrossOrigin(origins = "http://localhost:5173")  // Allow frontend requests
public class ResourceFileController {

    private static final Logger logger = LoggerFactory.getLogger(ResourceFileController.class);

    @Autowired  
    private ResourceFileService resourceFileService;

    // ✅ Upload File
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty. Please upload a valid file.");
            }
            ResourceFile savedFile = resourceFileService.storeFile(file);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFile);
        } catch (IOException e) {
            logger.error("File upload failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading file. Please try again.");
        }
    }

    // ✅ Fetch All Files
    @GetMapping("/all")
    public ResponseEntity<?> getAllResources() {
        List<ResourceFile> resources = resourceFileService.getAllFiles();
        if (resources.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No files found.");
        }
        return ResponseEntity.ok(resources);
    }

    // ✅ Search Files by Name
    @GetMapping("/search")
    public ResponseEntity<?> searchResources(@RequestParam String keyword) {
        List<ResourceFile> resources = resourceFileService.searchFiles(keyword);
        if (resources.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No files found matching the keyword: " + keyword);
        }
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable("id") Long id) {
        try {
            return resourceFileService.downloadFile(id);
        } catch (Exception e) {
            logger.error("Error downloading file: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error downloading file. Please try again.");
        }
    }



 // ✅ Delete File by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable("id") Long id) {
        try {
            resourceFileService.deleteFile(id);
            return ResponseEntity.ok("File with ID " + id + " deleted successfully.");
        } catch (EntityNotFoundException e) {
            logger.error("File not found with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("File not found with ID: " + id);
        } catch (Exception e) {
            logger.error("Error deleting file with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting file. Please try again.");
        }
    }

}
