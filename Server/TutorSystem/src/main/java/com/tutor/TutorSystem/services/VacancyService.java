package com.tutor.TutorSystem.services;

import com.tutor.TutorSystem.models.Vacancy;

import com.tutor.TutorSystem.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = false)
public class VacancyService {

    private final VacancyRepository vacancyRepository;

    @Autowired
    public VacancyService(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    public List<Vacancy> findAll() {
        return vacancyRepository.findAll();
    }

    public Vacancy findOne(int id) {
        Optional<Vacancy> foundPerson = vacancyRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public Vacancy save(Vacancy vacancy)
    {
        return vacancyRepository.save(vacancy);
    }

    public void delete(int id)
    {
        vacancyRepository.delete(vacancyRepository.findById(id).orElse(null));
    }
}