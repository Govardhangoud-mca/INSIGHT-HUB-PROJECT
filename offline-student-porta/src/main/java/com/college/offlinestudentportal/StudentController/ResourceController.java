package com.college.offlinestudentportal.StudentController;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.college.offlinestudentportal.entity.Resource;
import com.college.offlinestudentportal.service.ResourceService;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@CrossOrigin("http://localhost:5173")
public class ResourceController {
    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    // ✅ Get all resources
    @GetMapping("/all")
    public List<Resource> getAllResources() {
        return resourceService.getAllResources();
    }

    // ✅ Add a new resource
    @PostMapping("/add")
    public ResponseEntity<Resource> addResource(@RequestBody Resource resource) {
        Resource savedResource = resourceService.addResource(resource);
        return ResponseEntity.ok(savedResource);
    }

    // ✅ DELETE - Fixing Path Variable Issue
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteResource(@PathVariable("id") Long id) {
        boolean isDeleted = resourceService.deleteResource(id);
        if (isDeleted) {
            return ResponseEntity.ok("Resource deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Resource not found");
        }
    }
}


