package com.tutor.TutorSystem.services;

import com.tutor.TutorSystem.models.Student;

import com.tutor.TutorSystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findOne(int id) {
        Optional<Student> foundPerson = studentRepository.findById(id);
        return foundPerson.orElse(null);
    }
}