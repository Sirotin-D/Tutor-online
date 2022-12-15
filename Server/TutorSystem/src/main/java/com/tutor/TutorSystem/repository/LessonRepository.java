package com.tutor.TutorSystem.repository;

import com.tutor.TutorSystem.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    public List <Lesson> findByStudentId(int id);
    public List <Lesson> findByTutorId(int id);
}