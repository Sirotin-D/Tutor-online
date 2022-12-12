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

    fun saveUser(userData: User, userLogin: String) {
        val sharedEditor = settings?.edit()
        sharedEditor?.putString("user_mail", userLogin)
        sharedEditor?.putString("user_type", userData.userType.toString())
        sharedEditor?.putString("user_name", userData.name)
        sharedEditor?.putString("user_age", userData.age)
        sharedEditor?.apply()
    }


    fun getUserData(): Pair<User, String?> {
        val userMail = settings?.getString("user_mail", null)
        val userType = settings?.getString("user_type", null)
        val userName = settings?.getString("user_name", null)
        val userAge = settings?.getString("user_age", null)
        val user = User(userType, userName, userAge)
        return Pair(user, userMail)
    }
}