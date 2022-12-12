package com.example.tutor_online.service

import com.example.tutor_online.datamodel.User
import com.example.tutor_online.datamodel.UserType


class RequestService {
    fun getData(credentials: Pair<String, String>): User {
        val testName = "Тестовый пользователь"
        val testAge = "22"
        val userType = "Студент"
        return User(userType, testName, testAge)
    }
}