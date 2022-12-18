package com.example.tutor_online.service.requestservice.retrofit

import com.example.tutor_online.datamodel.*
import com.example.tutor_online.datamodel.requestdatamodels.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface RetrofitService {

    @GET("lesson")
    fun getLessons() : Call<MutableList<Lesson>>

    @GET("/user/{id}/my-lessons")
    fun getMyLessonList(
        @Path("id") userId: Int
    ): Call<MutableList<RequestLesson>>

    @GET("/lesson/{id}")
    fun getCurrentLesson(
        @Path("id") lessonId: Int
    ) : Call<Lesson>

    @GET("requestLesson/{id}")
    fun getCurrentRequestLesson(
        @Path("id") requestLessonId: Int
    ) : Call<RequestLesson>

    @POST("auth")
    fun auth(
        @Body userLogin: UserLogin
    ): Call<User>

    @POST("requestLesson/create")
    fun createRequestLesson(
        @Body requestLessonCreate: RequestLessonCreate
    ) : Call<ResponseBody>

    @PATCH("requestLesson/update")
    fun updateRequestLessonStatus(
        @Body updateStatusRequestLesson: UpdateStatusRequestLesson
    ): Call<ResponseBody>

    @POST("lesson/create")
    fun createLesson(
        @Body lessonCreate: LessonCreate
    ): Call<ResponseBody>

    @POST("user/create")
    fun createAccount(
        @Body userRegister: UserRegister
    ) : Call<User>
}