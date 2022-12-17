package com.tutor.TutorSystem.models;

import javax.persistence.*;

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @Column(name = "lesson_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lessonId;
    @Column(name = "lesson_tutor_id")
    private int tutorId;
    @Column(name = "lesson_tutor_name")
    private String tutorName;
    @Column(name = "lesson_title")
    private String title;
    @Column(name = "lesson_welcome_message")
    private String welcomeMessage;
    @Column(name = "lesson_description")
    private String description;

    public Lesson() {
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



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public Lesson(int lessonId, int tutorId, String tutorName, String title, String welcomeMessage, String description) {
        this.lessonId = lessonId;
        this.tutorId = tutorId;
        this.tutorName = tutorName;
        this.title = title;
        this.welcomeMessage = welcomeMessage;
        this.description = description;
    }
}
