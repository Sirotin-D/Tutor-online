package com.example.tutor_online.datamodel.viewdatamodel

import com.example.tutor_online.datamodel.User

enum class AuthViewDataModel(var errorMessage: String? = null, var user: User? = null) {
    INITIAL_STATE,
    SHOW_LOADING,
    HIDE_LOADING,
    SHOW_ERROR,
    OPEN_MAIN_MENU
}