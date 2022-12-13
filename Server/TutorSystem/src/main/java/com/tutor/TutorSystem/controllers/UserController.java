package com.tutor.TutorSystem.controllers;

import com.tutor.TutorSystem.models.Lesson;
import com.tutor.TutorSystem.models.LoginData;
import com.tutor.TutorSystem.models.User;
import com.tutor.TutorSystem.services.LessonService;
import com.tutor.TutorSystem.services.LoginDataService;
import com.tutor.TutorSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final LessonService lessonService;
    private final LoginDataService loginDataService;

    @Autowired
    public UserController(UserService studentService, LessonService lessonService, LoginDataService loginDataService) {
        this.userService = studentService;
        this.lessonService = lessonService;
        this.loginDataService = loginDataService;
    }

    @GetMapping()
    public List<User> getUsers(){
    return userService.findAll(); // Jackson конвертирует в json
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id")int id){
        return  userService.findOne(id);
    }

    @GetMapping("/{id}/my-lessons")
    public List<Lesson> findByStudent(@PathVariable("id")int studentId){
        return lessonService.findByStudentId(studentId);
    }

    @GetMapping("/{id}/my-order")
    public List<Lesson> findByTutor(@PathVariable("id")int tutorId){
        return lessonService.findByTutorId(tutorId);
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable("email")String email){
        return  userService.findByEmail(email);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody User user)
    {
        userService.save(user);
        return new ResponseEntity<HttpStatus>( HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id)
    {
        userService.delete(id);
        return new ResponseEntity<HttpStatus>( HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<HttpStatus> delete(@RequestBody User user)
    {
        userService.update(user);
        return new ResponseEntity<HttpStatus>( HttpStatus.OK);
    }

}
