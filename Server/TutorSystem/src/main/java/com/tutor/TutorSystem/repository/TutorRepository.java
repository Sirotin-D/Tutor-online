package com.tutor.TutorSystem.repository;

import com.tutor.TutorSystem.models.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Integer> {
}