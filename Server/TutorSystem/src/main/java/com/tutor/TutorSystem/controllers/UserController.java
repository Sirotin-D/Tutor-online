package com.tutor.TutorSystem.controllers;


import com.tutor.TutorSystem.POJOObjects.UserLoginCreater;
import com.tutor.TutorSystem.models.LoginData;
import com.tutor.TutorSystem.models.Request;
import com.tutor.TutorSystem.models.User;
import com.tutor.TutorSystem.services.RequestService;
import com.tutor.TutorSystem.services.LoginDataService;
import com.tutor.TutorSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final RequestService lessonService;
    private final LoginDataService loginDataService;

    @Autowired
    public UserController(UserService studentService, RequestService lessonService, LoginDataService loginDataService) {
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
    public List<Request> findByStudent(@PathVariable("id")int studentId){
        List<Request> result = new ArrayList<Request>(lessonService.findByStudentId(studentId));
        result.addAll(lessonService.findByTutorId(studentId));
        return result;
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable("email")String email){
        return  userService.findByEmail(email);
    }

    @PostMapping("/create")
    public User create(@RequestBody UserLoginCreater userLoginCreater)
    {
        User user = new User(userLoginCreater.getId(),userLoginCreater.getName(),userLoginCreater.getAge(),userLoginCreater.getEmail(),
                userLoginCreater.getType(),userLoginCreater.getPhone());
        LoginData loginData = new LoginData(userLoginCreater.getEmail(),userLoginCreater.getPassword());
        user = userService.save(user);
        loginDataService.save(loginData);
        return user;

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
