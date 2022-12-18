package com.example.tutor_online.datamodel.requestdatamodels

data class LessonCreate(
    val tutorId: Int,
    val tutorName: String,
    val title: String,
    val welcomeMessage: String,
    val description: String
)