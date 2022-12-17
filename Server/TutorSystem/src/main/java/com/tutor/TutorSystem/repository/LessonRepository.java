package com.tutor.TutorSystem.repository;

import com.tutor.TutorSystem.models.Lesson;
import com.tutor.TutorSystem.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
}