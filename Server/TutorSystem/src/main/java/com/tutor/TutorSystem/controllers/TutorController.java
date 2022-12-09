package com.tutor.TutorSystem.controllers;

import com.tutor.TutorSystem.models.Tutor;
import com.tutor.TutorSystem.services.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tutor")
public class TutorController {

    private final TutorService TutorService;

    @Autowired
    public TutorController(TutorService TutorService) {
        this.TutorService = TutorService;
    }

    @GetMapping()
    public List<Tutor> getTutors(){
        return TutorService.findAll(); // Jackson конвертирует в json
    }

    @GetMapping("/{id}")
    public Tutor getTutor(@PathVariable("id")int id){
        return  TutorService.findOne(id);
    }
}
