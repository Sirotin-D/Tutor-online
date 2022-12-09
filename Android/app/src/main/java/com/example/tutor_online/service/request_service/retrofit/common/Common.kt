package com.example.tutor_online.service.request_service.retrofit.common

import com.example.tutor_online.service.request_service.retrofit.RetrofitClient
import com.example.tutor_online.service.request_service.retrofit.RetrofitService

object Common {
    private val BASE_URL = "http://localhost:8080/"
    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}