package com.tutor.TutorSystem.POJOObjects;

import javax.persistence.Entity;

public class RequestCreater {
    int userId;
    int lessonId;

    public RequestCreater() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public RequestCreater(int userId, int lessonId) {
        this.userId = userId;
        this.lessonId = lessonId;
    }
}
