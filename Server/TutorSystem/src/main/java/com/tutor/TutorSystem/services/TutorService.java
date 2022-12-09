package com.tutor.TutorSystem.services;

import com.tutor.TutorSystem.models.Tutor;

import com.tutor.TutorSystem.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class TutorService {

    private final TutorRepository TutorRepository;

    @Autowired
    public TutorService(TutorRepository TutorRepository) {
        this.TutorRepository = TutorRepository;
    }

    public List<Tutor> findAll() {
        return TutorRepository.findAll();
    }

    public Tutor findOne(int id) {
        Optional<Tutor> foundPerson = TutorRepository.findById(id);
        return foundPerson.orElse(null);
    }
}