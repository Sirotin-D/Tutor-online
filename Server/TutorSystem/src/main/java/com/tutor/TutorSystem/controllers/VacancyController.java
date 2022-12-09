package com.tutor.TutorSystem.controllers;

import com.tutor.TutorSystem.models.Vacancy;
import com.tutor.TutorSystem.services.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vacancy")
public class VacancyController {

    private final VacancyService VacancyService;

    @Autowired
    public VacancyController(VacancyService VacancyService) {
        this.VacancyService = VacancyService;
    }

    @GetMapping()
    public List<Vacancy> getVacancys(){
        return VacancyService.findAll(); // Jackson конвертирует в json
    }

    @GetMapping("/{id}")
    public Vacancy getVacancy(@PathVariable("id")int id){
        return  VacancyService.findOne(id);
    }
}
