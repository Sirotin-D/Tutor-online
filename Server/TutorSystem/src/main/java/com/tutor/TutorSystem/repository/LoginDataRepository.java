package com.tutor.TutorSystem.repository;

import com.tutor.TutorSystem.models.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDataRepository extends JpaRepository<LoginData, Integer> {
    public LoginData findByEmail(String email);
}