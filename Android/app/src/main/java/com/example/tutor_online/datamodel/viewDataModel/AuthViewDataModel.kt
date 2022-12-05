package com.example.tutor_online.datamodel.viewDataModel

import com.example.tutor_online.datamodel.User

enum class AuthViewDataModel(var resourceId: Int? = null, var user: User? = null) {
    INITIAL_STATE,
    SHOW_LOADING,
    HIDE_LOADING,
    SHOW_ERROR,
    OPEN_MAIN_MENU
}