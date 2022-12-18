package com.example.tutor_online.service.requestservice

import com.example.tutor_online.datamodel.Lesson
import com.example.tutor_online.datamodel.RequestLesson
import com.example.tutor_online.datamodel.User
import retrofit2.Response

interface AuthResponseCallback {
    fun onSuccess(response: Response<User>)
    fun onFailure(throwable: Throwable)
}

interface LessonListResponseCallback {
    fun onSuccess(response: Response<MutableList<Lesson>>)
    fun onFailure(throwable: Throwable)
}

interface MyLessonListResponseCallback {
    fun onSuccess(response: Response<MutableList<RequestLesson>>)
    fun onFailure(throwable: Throwable)
}

interface CurrentLessonResponseCallback {
    fun onSuccess(response: Response<Lesson>)
    fun onFailure(throwable: Throwable)
}

interface CreateRequestLessonResponseCallback {
    fun onSuccess()
    fun onFailure(throwable: Throwable)
}

interface CreateLessonResponseCallback {
    fun onSuccess()
    fun onFailure(throwable: Throwable)
}

interface UpdateRequestLessonStatusResponseCallback {
    fun onSuccess()
    fun onFailure(throwable: Throwable)
}

interface CreateAccountResponseCallback {
    fun onSuccess(response: Response<User>)
    fun onFailure(throwable: Throwable)
}