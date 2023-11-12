package com.example.tutor_online.datamodel.requestdatamodels

data class UserRegister(
    val name: String,
    val age: Int,
    val phone: String,
    val email: String,
    val type: String,
    val password: String
)