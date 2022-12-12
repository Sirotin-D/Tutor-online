package com.example.tutor_online.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutor_online.ui.fragment.IBaseView
import com.example.tutor_online.datamodel.viewDataModel.AuthViewDataModel
import com.example.tutor_online.service.RequestService

class AuthViewModel(): ViewModel(), IBaseView {

    private val _authDisplayLiveData: MutableLiveData<AuthViewDataModel> = MutableLiveData()
    val authDisplayLiveData: LiveData<AuthViewDataModel> = _authDisplayLiveData


    override fun showLoading() {
        _authDisplayLiveData.postValue(AuthViewDataModel.SHOW_LOADING)
    }

    override fun hideLoading() {
        _authDisplayLiveData.postValue(AuthViewDataModel.HIDE_LOADING)
    }

    override fun showError(errorId: Int) {
        val authDisplayType = AuthViewDataModel.SHOW_ERROR
        authDisplayType.resourceId = errorId
        _authDisplayLiveData.postValue(authDisplayType)
    }

    fun viewOpened() {
        _authDisplayLiveData.postValue(AuthViewDataModel.INITIAL_STATE)
    }

    fun handleClickingOnSignIn(login: String, password: String) {
        showLoading()
        if ((login.isBlank()) || (password.isBlank())) {
            hideLoading()
            return
        }
        val user = RequestService().getData(Pair(login, password))

        val userState = AuthViewDataModel.OPEN_MAIN_MENU
        userState.user = user
        _authDisplayLiveData.postValue(userState)
    }
}