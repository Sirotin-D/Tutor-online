package com.example.tutor_online.datamodel

data class Lesson(
    val lesson_id: String,
    val lesson_title: String,
    val lesson_description: String,
    val lesson_tutor_name: String,
    val lesson_tutor_id: String,
    val lesson_welcome_message: String
)