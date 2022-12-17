package com.tutor.TutorSystem.models;

import javax.persistence.*;

@Entity
@Table(name = "request")
public class Request {
    @Id
    @Column(name = "request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int requestId;
    @Column(name = "request_lesson_id")
    int lessonId;
    @Column(name = "request_tutor_id")
    int tutorId;
    @Column(name = "request_student_id")
    int studentId;
    @Column(name = "request_status")
    String status;
    @Column(name = "request_welcome_message")
    String welcomeMessage;

    public Request() {
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Request(int requestId, int lessonId, int tutorId, int studentId, int vacancyId, String status, String welcomeMessage) {
        this.requestId = requestId;
        this.lessonId = lessonId;
        this.tutorId = tutorId;
        this.studentId = studentId;
        this.status = status;
        this.welcomeMessage = welcomeMessage;
    }

    public Request(int userId , Lesson lesson) {
        this.requestId = requestId;
        this.lessonId = lesson.getLessonId();
        this.tutorId = lesson.getTutorId();
        this.studentId = userId;
        this.status = "PENDING";
        this.welcomeMessage = lesson.getWelcomeMessage();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }
}
