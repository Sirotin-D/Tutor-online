package com.example.tutor_online.service.request_service.retrofit

import com.example.tutor_online.datamodel.User
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @GET("student")
    fun getStudents(): Call<MutableList<User>>
}