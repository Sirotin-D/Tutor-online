package com.example.tutor_online.service.requestservice.retrofit.common

import com.example.tutor_online.service.requestservice.retrofit.RetrofitClient
import com.example.tutor_online.service.requestservice.retrofit.RetrofitService

object Common {
    private const val BASE_URL = "http://localhost:8080/"
    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}