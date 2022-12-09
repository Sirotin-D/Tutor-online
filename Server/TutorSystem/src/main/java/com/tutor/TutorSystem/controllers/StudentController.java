package com.tutor.TutorSystem.controllers;

import com.tutor.TutorSystem.models.Student;
import com.tutor.TutorSystem.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public List<Student> getStudents(){
    return studentService.findAll(); // Jackson конвертирует в json
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id")int id){
        return  studentService.findOne(id);
    }
}
