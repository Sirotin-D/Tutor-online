package com.tutor.TutorSystem.models;

import javax.persistence.*;

@Entity
@Table(name = "vacancy")
public class Vacancy {
    @Id
    @Column(name = "vacancy_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vacancyId;
    @Column(name = "tutor_id")
    private int tutorId;
    @Column(name = "status")
    private int status;
    @Column(name = "filds")
    private String fields;

    public Vacancy(int vacancyId, int tutorId, int status, String fields) {
        this.vacancyId = vacancyId;
        this.tutorId = tutorId;
        this.status = status;
        this.fields = fields;
    }

    public Vacancy() {
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }
}
