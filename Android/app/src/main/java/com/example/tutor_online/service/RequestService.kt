package com.example.tutor_online.service

import com.example.tutor_online.datamodel.Lesson
import com.example.tutor_online.datamodel.User


class RequestService {
    fun auth(credentials: Pair<String, String>): User {
        val testName = "Тестовый пользователь"
        val testAge = "22"
        val userType = "Студент"
        return User(userType, testName, testAge)
    }

    fun getLessonList(): List<Lesson> {
        val testLessonsList = mutableListOf<Lesson>()
        testLessonsList.add(Lesson("test_lesson_id","test_title1", "test_description1", "test_tutor1", "tutor_id"))
        testLessonsList.add(Lesson("test_lesson_id","test_title2", "test_description2", "test_tutor1", "tutor_id"))
        testLessonsList.add(Lesson("test_lesson_id","test_title3", "test_description3", "test_tutor1", "tutor_id"))
        testLessonsList.add(Lesson("test_lesson_id","test_title4", "test_description4", "test_tutor1", "tutor_id"))
        testLessonsList.add(Lesson("test_lesson_id","test_title5", "test_description5", "test_tutor1", "tutor_id"))
        testLessonsList.add(Lesson("test_lesson_id","test_title6", "test_description6", "test_tutor1", "tutor_id"))
        return testLessonsList
    }

    fun getMyLessonList(): List<Lesson> {
        val testLessonsList = mutableListOf<Lesson>()
        testLessonsList.add(Lesson("test_lesson_id","test_title1", "test_description1", "test_tutor1", "tutor_id"))
        testLessonsList.add(Lesson("test_lesson_id","test_title2", "test_description2", "test_tutor1", "tutor_id"))
        testLessonsList.add(Lesson("test_lesson_id","test_title3", "test_description3", "test_tutor1", "tutor_id"))
        testLessonsList.add(Lesson("test_lesson_id","test_title4", "test_description4", "test_tutor1", "tutor_id"))
        return testLessonsList
    }
}