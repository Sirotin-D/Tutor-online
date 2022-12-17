package com.tutor.TutorSystem.models;

import javax.persistence.*;

@Entity
@Table(name = "login")
public class LoginData {
    @Id
    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;

    public LoginData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginData() {
    }
}
