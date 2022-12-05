package com.example.tutor_online.service

import com.example.tutor_online.datamodel.User
import com.example.tutor_online.datamodel.UserType


class RequestService {
    fun getData(credentials: Pair<String, String>): User {
        val testName = credentials.first
        val testAge = "22"
        val testDescription = "Some description"
        val userType = UserType.STUDENT
        return User(userType, testName, testAge, testDescription)
    }
}