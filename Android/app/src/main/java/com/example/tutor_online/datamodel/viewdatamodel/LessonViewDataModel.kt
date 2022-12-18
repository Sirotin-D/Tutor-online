package com.example.tutor_online.datamodel.viewdatamodel

enum class LessonViewDataModel(var errorMessage: String? = null) {
    INITIAL_STATE,
    SHOW_ERROR,
    LESSON_REQUESTED
}