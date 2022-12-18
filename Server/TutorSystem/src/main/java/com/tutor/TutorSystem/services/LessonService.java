package com.tutor.TutorSystem.services;
import com.tutor.TutorSystem.models.Lesson;
import com.tutor.TutorSystem.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = false)
public class LessonService {

    private final LessonRepository lessonRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    public Lesson findOne(int id) {
        Optional<Lesson> foundPerson = lessonRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public Lesson save(Lesson vacancy)
    {
        return lessonRepository.save(vacancy);
    }

    public void delete(int id)
    {
        lessonRepository.delete(lessonRepository.findById(id).orElse(null));
    }
}