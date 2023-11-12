package com.example.tutor_online.datamodel

data class Lesson(
    val lessonId: Int,
    val tutorId: Int,
    val tutorName: String,
    val title: String,
    val welcomeMessage: String,
    val description: String
)