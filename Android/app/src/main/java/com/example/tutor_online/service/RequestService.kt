package com.example.tutor_online.service

import com.example.tutor_online.datamodel.Lesson
import com.example.tutor_online.datamodel.RequestLesson
import com.example.tutor_online.datamodel.User


class RequestService {
    fun auth(credentials: Pair<String, String>): User {
        return getTestUser()
    }

    fun getLessonList(): List<Lesson> {
        return getTestLessonList()
    }

    fun getMyLessonList(): List<RequestLesson> {
        return getTestMyLessonList()
    }

    fun requestLesson(userId: String, lessonId: String) {

    }

    private fun getTestUser(): User {
        val testUserId = "1"
        val testUserName = "Тестовый пользователь"
        val testUserAge = "22"
        val testUserPhone = "+79101234567"
        val testUserEmail = "test_email@unn.ru"
        val testUserType = "student"
        return User(testUserId, testUserName, testUserAge, testUserPhone, testUserEmail,  testUserType)
    }

    private fun getTestLessonList(): List<Lesson> {
        val testLessonsList = mutableListOf<Lesson>()
        testLessonsList.add(Lesson(
            "1",
            "Математика для EГЭ",
            "Подготовка к ЕГЭ",
            "Мария Ивановна",
            "4",
            "Для связи звонить на номер 4567"))
        testLessonsList.add(Lesson(
            "2",
            "Русский язык",
            "5-11 класс",
            "Мария Ивановна",
            "4",
            "Для связи звонить на номер 4568"))
        testLessonsList.add(Lesson(
            "3",
            "Обществознание",
            "Подготовка к ОГЭ",
            "Мария Ивановна",
            "4",
            "Для связи звонить на номер 4569"))
        testLessonsList.add(Lesson(
            "4",
            "Английский для первоклассников",
            "1-4 класс",
            "Мария Ивановна",
            "4",
            "Для связи звонить на номер 4570"))
        return testLessonsList
    }

    private fun getTestMyLessonList(): List<RequestLesson> {
        val testLessonsList = mutableListOf<RequestLesson>()
        testLessonsList.add(RequestLesson(
            "1",
            "1",
            "Математика для EГЭ",
            "Подготовка к ЕГЭ",
            "Мария Ивановна",
            "4",
            "Для связи звонить на номер 4567",
            "1",
            "pending"))
        testLessonsList.add(RequestLesson(
            "2",
            "2",
            "Русский язык",
            "5-11 класс",
            "Мария Ивановна",
            "4",
            "Для связи звонить на номер 4568",
            "1",
            "accepted"))
        testLessonsList.add(RequestLesson(
            "3",
            "3",
            "Обществознание",
            "Подготовка к ОГЭ",
            "Мария Ивановна",
            "4",
            "Для связи звонить на номер 4569",
            "1",
            "cancelled"))
        return testLessonsList
    }
}