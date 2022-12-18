package com.example.tutor_online.datamodel

enum class RequestLessonStatus {
    CANCELLED {
        override fun toString(): String {
            return "Урок отменён"
        }
    },
    ACCEPTED {
        override fun toString(): String {
            return "Урок принят"
        }
    },
    PENDING {
        override fun toString(): String {
            return "Ожидает подтверждения преподавателем"
        }
    }
}