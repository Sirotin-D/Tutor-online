package com.tutor.TutorSystem.controllers;

import com.tutor.TutorSystem.models.Lesson;
import com.tutor.TutorSystem.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService vacancyService) {
        this.lessonService = vacancyService;
    }

    @GetMapping()
    public List<Lesson> getVacancys(){
        return lessonService.findAll(); // Jackson конвертирует в json
    }

    @GetMapping("/{id}")
    public Lesson getVacancy(@PathVariable("id")int id){
        return  lessonService.findOne(id);
    }

    @PostMapping("/create")
    public Lesson save(@RequestBody Lesson vacancy){
        return lessonService.save(vacancy);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@RequestBody @PathVariable("id")int id){
        lessonService.delete(id);
    }
}
