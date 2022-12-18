package com.example.tutor_online.datamodel.viewdatamodel

import com.example.tutor_online.datamodel.Lesson

enum class RequestLessonViewDataModel (var errorMessage: String? = null, var lesson: Lesson? = null) {
    SHOW_LOADING,
    HIDE_LOADING,
    SHOW_PENDING_LESSON_FOR_STUDENT,
    SHOW_PENDING_LESSON_FOR_TUTOR,
    SHOW_ACCEPTED_LESSON,
    SHOW_CANCELLED_LESSON,
    SHOW_LESSON_AFTER_BUTTON_PRESSED,
    SHOW_ERROR,
}