package com.tutor.TutorSystem;

import com.tutor.TutorSystem.repository.LoginDataRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public abstract class TutorSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(TutorSystemApplication.class, args);
	}
}
