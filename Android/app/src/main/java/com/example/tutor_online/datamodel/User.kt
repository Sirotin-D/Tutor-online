package com.example.tutor_online.datamodel

data class User (
    val userType: UserType,
    val name: String,
    val age: String?,
    val description: String?
)