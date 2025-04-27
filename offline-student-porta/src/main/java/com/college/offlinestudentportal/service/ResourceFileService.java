package com.college.offlinestudentportal.service;


import com.college.offlinestudentportal.StudentRepository.ResourceFileRepository;
import com.college.offlinestudentportal.entity.ResourceFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
public class ResourceFileService {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private ResourceFileRepository resourceFileRepository;

    // ✅ Store File
    public ResourceFile storeFile(MultipartFile file) throws IOException {
        if (!Files.exists(Paths.get(UPLOAD_DIR))) {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
        }

        String filePath = UPLOAD_DIR + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        ResourceFile resourceFile = new ResourceFile();
        resourceFile.setFileName(file.getOriginalFilename());
        resourceFile.setFileType(file.getContentType());
        resourceFile.setFilePath(filePath);
        resourceFile.setFileSize(String.valueOf(file.getSize()));

        return resourceFileRepository.save(resourceFile);
    }

    // ✅ Get All Files
    public List<ResourceFile> getAllFiles() {
        return resourceFileRepository.findAll();
    }

    // ✅ Search Files
    public List<ResourceFile> searchFiles(String keyword) {
        return resourceFileRepository.findByFileNameContainingIgnoreCase(keyword);
    }

    // ✅ Download File
    public ResponseEntity<Resource> downloadFile(Long id) throws IOException {
        ResourceFile resourceFile = resourceFileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));

        Path filePath = Paths.get(resourceFile.getFilePath());
        Resource resource = new UrlResource(filePath.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resourceFile.getFileName() + "\"")
                .body(resource);
    }

    // ✅ Delete File
    public void deleteFile(Long id) {
        ResourceFile resourceFile = resourceFileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));

        Path filePath = Paths.get(resourceFile.getFilePath());
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Error deleting file", e);
        }

        resourceFileRepository.delete(resourceFile);
    }
}
