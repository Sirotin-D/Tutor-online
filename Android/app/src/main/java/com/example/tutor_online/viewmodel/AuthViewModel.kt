package com.example.tutor_online.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutor_online.ui.fragment.IBaseView
import com.example.tutor_online.datamodel.viewDataModel.AuthViewDataModel
import com.example.tutor_online.service.RequestService

class AuthViewModel: ViewModel() {

    private val _authDisplayLiveData: MutableLiveData<AuthViewDataModel> = MutableLiveData()
    val authDisplayLiveData: LiveData<AuthViewDataModel> = _authDisplayLiveData

    private val requestService = RequestService()

    fun viewOpened() {
        _authDisplayLiveData.postValue(AuthViewDataModel.INITIAL_STATE)
    }

    fun handleClickingOnSignIn(login: String, password: String) {
        if ((login.isBlank()) || (password.isBlank())) {
            return
        }
        val user = requestService.auth(Pair(login, password))
        val userState = AuthViewDataModel.OPEN_MAIN_MENU
        userState.user = user
        _authDisplayLiveData.postValue(userState)
    }
}