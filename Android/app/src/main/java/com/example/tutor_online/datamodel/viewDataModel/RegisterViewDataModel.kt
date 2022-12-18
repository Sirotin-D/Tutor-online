package com.example.tutor_online.datamodel.viewDataModel

import com.example.tutor_online.datamodel.User

enum class RegisterViewDataModel(var user: User? = null) {
    INITIAL_STATE,
    END_REGISTER,
}