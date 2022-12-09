package com.tutor.TutorSystem.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Student {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="second_name")
    private String secondName;

    @Column(name="age")
    private int age;

    @Column(name="email")
    private String email;

    public Student() {
    }

    public Student(int id, String name, String surname, String secondName, int age, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.secondName = secondName;
        this.age = age;
        this.email = email;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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


}
