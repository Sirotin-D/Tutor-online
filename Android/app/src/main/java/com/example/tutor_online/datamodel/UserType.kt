package com.example.tutor_online.datamodel

enum class UserType {
    TUTOR {
        override fun toString(): String {
            return "Преподаватель"
        }
    },
    STUDENT {
        override fun toString(): String {
            return "Студент"
        }
    }
}