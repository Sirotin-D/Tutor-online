package com.tutor.TutorSystem.controllers;

import com.tutor.TutorSystem.models.Lesson;
import com.tutor.TutorSystem.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {

    private final LessonService LessonService;

    @Autowired
    public LessonController(LessonService LessonService) {
        this.LessonService = LessonService;
    }

    @GetMapping()
    public List<Lesson> getLessons(){
        return LessonService.findAll(); // Jackson конвертирует в json
    }

    @GetMapping("/{id}")
    public Lesson getLesson(@PathVariable("id")int id){
        return  LessonService.findOne(id);
    }
}
