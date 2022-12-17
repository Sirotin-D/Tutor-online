package com.example.tutor_online.datamodel

data class RequestLesson (
    val request_id: String,
    val request_lesson_id: String,
    val request_lesson_title: String,
    val request_lesson_description: String,
    val request_lesson_tutor_name: String,
    val request_tutor_id: String,
    val request_welcome_message: String,
    val request_student_id: String,
    val request_status: String,
)