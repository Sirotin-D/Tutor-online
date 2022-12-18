package com.tutor.TutorSystem.controllers;

import com.tutor.TutorSystem.models.LoginData;
import com.tutor.TutorSystem.models.User;
import com.tutor.TutorSystem.services.LoginDataService;
import com.tutor.TutorSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginDataController {
    private final LoginDataService loginDataService;
    private final UserService userService;

    @Autowired
    public LoginDataController(LoginDataService loginDataService, UserService userService) {
        this.loginDataService = loginDataService;
        this.userService = userService;
    }

    @PostMapping()
    public User getUserByEmailAndPassword(@RequestBody LoginData loginData)
    {
        return loginDataService.isRightPasswordForEmail(loginData)?
                userService.findByEmail(loginData.getEmail()):null;
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody LoginData loginData) {
        loginDataService.save(loginData);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
}
