package com.tutor.TutorSystem.services;

import com.tutor.TutorSystem.models.Lesson;

import com.tutor.TutorSystem.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class LessonService {

    private final LessonRepository LessonRepository;

    @Autowired
    public LessonService(LessonRepository LessonRepository) {
        this.LessonRepository = LessonRepository;
    }

    public List<Lesson> findAll() {
        return LessonRepository.findAll();
    }

    public Lesson findOne(int id) {
        Optional<Lesson> foundPerson = LessonRepository.findById(id);
        return foundPerson.orElse(null);
    }
}