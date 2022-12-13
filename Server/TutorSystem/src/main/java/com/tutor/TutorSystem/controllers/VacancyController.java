package com.tutor.TutorSystem.controllers;

import com.tutor.TutorSystem.models.Vacancy;
import com.tutor.TutorSystem.services.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacancy")
public class VacancyController {

    private final VacancyService vacancyService;

    @Autowired
    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping()
    public List<Vacancy> getVacancys(){
        return vacancyService.findAll(); // Jackson конвертирует в json
    }

    @GetMapping("/{id}")
    public Vacancy getVacancy(@PathVariable("id")int id){
        return  vacancyService.findOne(id);
    }

    @PostMapping("/create")
    public Vacancy save(@RequestBody Vacancy vacancy){
        return vacancyService.save(vacancy);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@RequestBody @PathVariable("id")int id){
        vacancyService.delete(id);
    }
}
