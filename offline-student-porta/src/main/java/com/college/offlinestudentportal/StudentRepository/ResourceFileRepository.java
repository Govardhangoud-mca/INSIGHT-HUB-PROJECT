package com.college.offlinestudentportal.StudentRepository;


import com.college.offlinestudentportal.entity.ResourceFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResourceFileRepository extends JpaRepository<ResourceFile, Long> {
    List<ResourceFile> findByFileNameContainingIgnoreCase(String keyword);
}
