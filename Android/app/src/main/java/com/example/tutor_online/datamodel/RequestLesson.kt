package com.example.tutor_online.datamodel

data class RequestLesson(
    val requestId: Int,
    val lessonId: Int,
    val tutorId: Int,
    val studentId: Int,
    val status: String,
    val welcomeMessage: String
)