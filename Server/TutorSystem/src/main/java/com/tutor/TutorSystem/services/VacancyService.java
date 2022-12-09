package com.tutor.TutorSystem.services;

import com.tutor.TutorSystem.models.Vacancy;

import com.tutor.TutorSystem.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class VacancyService {

    private final VacancyRepository VacancyRepository;

    @Autowired
    public VacancyService(VacancyRepository VacancyRepository) {
        this.VacancyRepository = VacancyRepository;
    }

    public List<Vacancy> findAll() {
        return VacancyRepository.findAll();
    }

    public Vacancy findOne(int id) {
        Optional<Vacancy> foundPerson = VacancyRepository.findById(id);
        return foundPerson.orElse(null);
    }
}