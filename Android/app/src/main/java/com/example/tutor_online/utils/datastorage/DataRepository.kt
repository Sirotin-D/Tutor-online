package com.example.tutor_online.utils.datastorage

import android.content.Context
import android.content.SharedPreferences
import com.example.tutor_online.datamodel.User


class DataRepository(context: Context?) {

    private val STORAGE_NAME = "TUTOR-ONLINE"
    private var settings: SharedPreferences? = null

    init {
        settings = context?.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
    }

    fun saveUser(userData: User) {
        val sharedEditor = settings?.edit()
        sharedEditor?.putString("user_id", userData.user_id)
        sharedEditor?.putString("user_name", userData.user_name)
        sharedEditor?.putString("user_age", userData.user_age)
        sharedEditor?.putString("user_phone", userData.user_phone)
        sharedEditor?.putString("user_email", userData.user_email)
        sharedEditor?.putString("user_type", userData.user_type)
        sharedEditor?.apply()
    }


    fun getUserData(): User {
        val userId = settings?.getString("user_id", null)
        val userName = settings?.getString("user_name", null)
        val userAge = settings?.getString("user_age", null)
        val userPhone = settings?.getString("user_phone", null)
        val userEmail = settings?.getString("user_email", null)
        val userType = settings?.getString("user_type", null)
        return User(userId, userName, userAge, userPhone, userEmail, userType)
    }
}