package com.tutor.TutorSystem.services;

import com.tutor.TutorSystem.models.LoginData;
import com.tutor.TutorSystem.repository.LoginDataRepository;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class LoginDataService {
    private final LoginDataRepository loginDataRepository;

    @Autowired
    public LoginDataService(LoginDataRepository loginDataRepository) {
        this.loginDataRepository = loginDataRepository;
    }

    public void save(LoginData loginData) {
        loginDataRepository.save(loginData);
    }

    public boolean isRightPasswordForEmail(LoginData loginData){
        String rightPassword = loginDataRepository
                .findByEmail(loginData.getEmail()).getPassword();
        boolean result = rightPassword.equals(loginData.getPassword());
        return result;
    }
}

