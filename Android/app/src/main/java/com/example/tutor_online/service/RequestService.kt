package com.example.tutor_online.service

import android.util.Log
import com.example.tutor_online.datamodel.User
import com.example.tutor_online.service.request_service.retrofit.RetrofitService
import com.example.tutor_online.service.request_service.retrofit.common.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestService {
    fun getData(credentials: Pair<String, String>): User {
        val testName = credentials.first
        val testAge = 22
        val testDescription = "Some description"
        val userType = "testUserType"
        val testSurname = "testSurname"
        val testId = 0
        return User(userType, testId, testName, testSurname, testSurname, testAge, testDescription)
    }

    fun retrofitGetStudentData() {
        val mService: RetrofitService = Common.retrofitService
        mService.getStudents().enqueue(object : Callback<MutableList<User>> {
            override fun onResponse(
                call: Call<MutableList<User>>,
                response: Response<MutableList<User>>
            ) {
                Log.d("Test", "Successfully got data from server. Response: $response, data: ${response.body()}")
            }

            override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {
                Log.d("Test", "Error response. Call: $call, throwable: $t")
            }
        })
    }
}