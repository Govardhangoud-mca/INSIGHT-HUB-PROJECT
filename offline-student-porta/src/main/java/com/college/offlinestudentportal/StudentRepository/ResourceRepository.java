package com.college.offlinestudentportal.StudentRepository;




import org.springframework.data.jpa.repository.JpaRepository;

import com.college.offlinestudentportal.entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
