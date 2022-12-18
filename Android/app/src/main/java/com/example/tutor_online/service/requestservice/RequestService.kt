package com.example.tutor_online.service.requestservice

import com.example.tutor_online.datamodel.*
import com.example.tutor_online.datamodel.requestdatamodels.*
import com.example.tutor_online.service.requestservice.retrofit.RetrofitService
import com.example.tutor_online.service.requestservice.retrofit.common.Common
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RequestService {

    private var retrofitService: RetrofitService = Common.retrofitService

    fun auth(email: String, password: String, responseCallback: AuthResponseCallback) {
        val userLogin = UserLogin(email, password)
        retrofitService.auth(userLogin).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                responseCallback.onSuccess(response)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                responseCallback.onFailure(t)
            }
        })
    }

    fun getLessonList(responseCallback: LessonListResponseCallback){
        retrofitService.getLessons().enqueue(object: Callback<MutableList<Lesson>> {
            override fun onResponse(
                call: Call<MutableList<Lesson>>,
                response: Response<MutableList<Lesson>>
            ) {
                responseCallback.onSuccess(response)
            }

            override fun onFailure(call: Call<MutableList<Lesson>>, t: Throwable) {
                responseCallback.onFailure(t)
            }
        })
    }

    fun getMyLessonList(userId: Int, responseCallback: MyLessonListResponseCallback) {
        retrofitService.getMyLessonList(userId).enqueue(object : Callback<MutableList<RequestLesson>> {
            override fun onResponse(
                call: Call<MutableList<RequestLesson>>,
                response: Response<MutableList<RequestLesson>>
            ) {
                responseCallback.onSuccess(response)
            }
            override fun onFailure(call: Call<MutableList<RequestLesson>>, t: Throwable) {
                responseCallback.onFailure(t)
            }
        })
    }

    fun getCurrentLesson(lessonId: Int, responseCallback: CurrentLessonResponseCallback) {
        retrofitService.getCurrentLesson(lessonId).enqueue(object : Callback<Lesson> {
            override fun onResponse(call: Call<Lesson>, response: Response<Lesson>) {
                responseCallback.onSuccess(response)
            }
            override fun onFailure(call: Call<Lesson>, t: Throwable) {
                responseCallback.onFailure(t)
            }
        })
    }

    fun requestLesson(userId: Int, lessonId: Int, responseCallback: CreateRequestLessonResponseCallback) {
        val requestLessonCreate = RequestLessonCreate(userId, lessonId)
        retrofitService.createRequestLesson(requestLessonCreate).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                responseCallback.onSuccess()
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                responseCallback.onFailure(t)
            }

        })
    }

    fun createLesson(tutorId: Int, tutorName: String, lessonTitle: String, lessonContactData: String, lessonDescription: String, responseCallback: CreateLessonResponseCallback) {
        val lessonCreate = LessonCreate(tutorId, tutorName, lessonTitle, lessonContactData, lessonDescription)
        retrofitService.createLesson(lessonCreate).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                responseCallback.onSuccess()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                responseCallback.onFailure(t)
            }
        })
    }

    fun updateRequestLessonStatus(requestId: Int, status: RequestLessonStatus, responseCallback: UpdateRequestLessonStatusResponseCallback) {
        val updateStatusRequestLesson = UpdateStatusRequestLesson(requestId, status.name)
        retrofitService.updateRequestLessonStatus(updateStatusRequestLesson).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                responseCallback.onSuccess()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                responseCallback.onFailure(t)
            }

        })
    }

    fun createAccount(name: String, age: Int, phone: String, email: String, password: String, userType: UserType, responceCallback: CreateAccountResponseCallback) {
        val userRegister = UserRegister(name, age, phone, email, userType.name, password)
        retrofitService.createAccount(userRegister).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                responceCallback.onSuccess(response)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                responceCallback.onFailure(t)
            }
        })
    }
}