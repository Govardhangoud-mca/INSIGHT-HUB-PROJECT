package com.college.offlinestudentportal.service;




import org.springframework.stereotype.Service;

import com.college.offlinestudentportal.StudentRepository.ResourceRepository;
import com.college.offlinestudentportal.entity.Resource;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {
    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    public Resource addResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    public boolean deleteResource(Long id) {
        Optional<Resource> resource = resourceRepository.findById(id);
        if (resource.isPresent()) {
            resourceRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}



