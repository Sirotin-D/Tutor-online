package com.example.tutor_online.datamodel.viewdatamodel

import com.example.tutor_online.datamodel.User

enum class RegisterViewDataModel(var errorMessage: String? = null, var user: User? = null) {
    INITIAL_STATE,
    SHOW_ERROR,
    OPEN_MAIN_MENU
}