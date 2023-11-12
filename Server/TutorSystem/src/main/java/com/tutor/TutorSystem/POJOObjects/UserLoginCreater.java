package com.tutor.TutorSystem.POJOObjects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserLoginCreater {

    private int id;
    private String name;
    private int age;
    private String email;
    private String type;
    private String phone;
    private String password;

    public UserLoginCreater() {
    }

    public UserLoginCreater(int id, String name, int age, String email, String type, String phone, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.type = type;
        this.phone = phone;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
