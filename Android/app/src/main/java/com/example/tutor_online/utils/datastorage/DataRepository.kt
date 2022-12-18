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
        sharedEditor?.putInt("user_id", userData.id)
        sharedEditor?.putString("user_name", userData.name)
        sharedEditor?.putInt("user_age", userData.age)
        sharedEditor?.putString("user_phone", userData.phone)
        sharedEditor?.putString("user_email", userData.email)
        sharedEditor?.putString("user_type", userData.type)
        sharedEditor?.apply()
    }

    fun getUserData(): User {
        val userId = settings?.getInt("user_id", 0)
        val userName = settings?.getString("user_name", null)
        val userAge = settings?.getInt("user_age", 0)
        val userPhone = settings?.getString("user_phone", null)
        val userEmail = settings?.getString("user_email", null)
        val userType = settings?.getString("user_type", null)
        return User(userId!!, userName!!, userAge!!, userEmail!!, userType!!, userPhone!!)
    }

    fun deleteUserData() {
        settings?.edit()?.clear()?.apply()
    }
}