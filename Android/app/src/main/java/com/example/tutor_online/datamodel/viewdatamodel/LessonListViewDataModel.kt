package com.example.tutor_online.datamodel.viewdatamodel

import com.example.tutor_online.datamodel.Lesson

enum class LessonListViewDataModel(var errorMessage: String? = null) {
    SHOW_LOADING,
    HIDE_LOADING,
    SHOW_ERROR,
    OPEN_LESSON
}