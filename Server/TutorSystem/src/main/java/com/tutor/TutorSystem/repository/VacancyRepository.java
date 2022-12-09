package com.tutor.TutorSystem.repository;

import com.tutor.TutorSystem.models.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {
}