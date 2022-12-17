package com.tutor.TutorSystem.services;

import com.tutor.TutorSystem.models.Request;

import com.tutor.TutorSystem.repository.LessonRepository;
import com.tutor.TutorSystem.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = false)
public class RequestService {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public Request findOne(int id) {
        Optional<Request> foundPerson = requestRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public List<Request> findByStudentId(int studentId){
        List<Request> foundPerson = requestRepository.findByStudentId(studentId);
        return foundPerson;
    }

    public List<Request> findByTutorId(int tutorId){
        List<Request> foundPerson = requestRepository.findByTutorId(tutorId);
        return foundPerson;
    }

    public boolean save(Request lesson) {
        try {
            requestRepository.save(lesson);
        } catch(Exception e)
        {
            return false;
        }
        return true;
    }

    public void delete(int id) {
        requestRepository.delete(requestRepository.findById(id).orElse(null));
    }

    public void update(Request lesson){
        requestRepository.save(lesson);
    }
}