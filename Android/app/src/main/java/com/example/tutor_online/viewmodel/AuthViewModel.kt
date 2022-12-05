package com.example.tutor_online.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutor_online.ui.fragment.IAuthView
import datamodel.viewdatamodel.AuthViewDataModel

class AuthViewModel(): ViewModel(), IAuthView {

    private val _authDisplayLiveData: MutableLiveData<AuthViewDataModel> = MutableLiveData()
    val authDisplayLiveData: LiveData<AuthViewDataModel> = _authDisplayLiveData


    override fun showLoading() {
        _authDisplayLiveData.postValue(AuthViewDataModel.SHOW_LOADING)
    }

    override fun hideLoading() {
        _authDisplayLiveData.postValue(AuthViewDataModel.HIDE_LOADING)
    }

    override fun showSignIn() {
        _authDisplayLiveData.postValue(AuthViewDataModel.SHOW_SIGN_IN)
    }

    override fun showError(errorId: Int) {
        val authDisplayType = AuthViewDataModel.SHOW_ERROR
        authDisplayType.resourceId = errorId
        _authDisplayLiveData.postValue(authDisplayType)
    }

    fun handleClickingOnSignIn(login: String, password: String) {
        showLoading()
        if ((login.isBlank()) && (password.isBlank())) {
            return
        }
        Log.d("Dmitry", "Login: $login, password: $password")
        _authDisplayLiveData.postValue(AuthViewDataModel.OPEN_MAIN_MENU)
    }
}