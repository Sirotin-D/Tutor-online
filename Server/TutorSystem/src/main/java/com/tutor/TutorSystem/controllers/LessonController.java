package com.tutor.TutorSystem.controllers;

import com.tutor.TutorSystem.models.Lesson;
import com.tutor.TutorSystem.models.User;
import com.tutor.TutorSystem.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService LessonService) {
        this.lessonService = LessonService;
    }

    @GetMapping()
    public List<Lesson> getLessons(){
        return lessonService.findAll(); // Jackson конвертирует в json
    }

    @GetMapping("/{id}")
    public Lesson getLesson(@PathVariable("id")int id){
        return  lessonService.findOne(id);
    }



    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody Lesson lesson)
    {
        lessonService.save(lesson);
        return new ResponseEntity<HttpStatus>( HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id)
    {
        lessonService.delete(id);
        return new ResponseEntity<HttpStatus>( HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<HttpStatus> delete(@RequestBody Lesson lesson )
    {
        lessonService.update(lesson);
        return new ResponseEntity<HttpStatus>( HttpStatus.OK);
    }
}
