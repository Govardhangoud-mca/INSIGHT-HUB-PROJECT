package com.college.offlinestudentportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.college.offlinestudentportal.StudentRepository.SubjectRepository;
import com.college.offlinestudentportal.dto.SubjectDto;
import com.college.offlinestudentportal.entity.Subject;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject addSubject(SubjectDto subjectDto) {
        Subject subject = new Subject(
            subjectDto.getTitle(),
            subjectDto.getTutorName(),
            subjectDto.getDepartment(),
            subjectDto.getSemester(),
            subjectDto.getPaymentAmount()
        );
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public List<Subject> getSubjectsByDepartmentAndSemester(String department, int semester) {
        return subjectRepository.findByDepartmentAndSemester(department, semester);
    }

    public Subject updateSubject(Long id, SubjectDto subjectDto) {
        Subject existingSubject = subjectRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));

        existingSubject.setTitle(subjectDto.getTitle());
        existingSubject.setTutorName(subjectDto.getTutorName());
        existingSubject.setDepartment(subjectDto.getDepartment());
        existingSubject.setSemester(subjectDto.getSemester());
        existingSubject.setPaymentAmount(subjectDto.getPaymentAmount());

        return subjectRepository.save(existingSubject);
    }

    public boolean deleteSubject(Long id) {
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
