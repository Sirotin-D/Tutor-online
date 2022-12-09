package com.tutor.TutorSystem.models;

import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @Column(name = "lesson_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int lessonId;
    @Column(name = "tutor_id")
    int tutorId;
    @Column(name = "student_id")
    int studentId;
    @Column(name = "vacancy_id")
    int vacancyId;
    @Column(name = "status_id")
    int status;

    public Lesson() {
    }

    public Lesson(int lessonId, int tutorId, int studentId, int vacancyId, int status) {
        this.lessonId = lessonId;
        this.tutorId = tutorId;
        this.studentId = studentId;
        this.vacancyId = vacancyId;
        this.status = status;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
