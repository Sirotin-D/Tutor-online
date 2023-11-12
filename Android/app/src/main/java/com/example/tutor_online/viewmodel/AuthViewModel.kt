package com.example.tutor_online.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutor_online.datamodel.User
import com.example.tutor_online.datamodel.viewdatamodel.AuthViewDataModel
import com.example.tutor_online.service.requestservice.AuthResponseCallback
import com.example.tutor_online.service.requestservice.RequestService
import retrofit2.Response

class AuthViewModel: ViewModel() {

    private val _authDisplayLiveData: MutableLiveData<AuthViewDataModel> = MutableLiveData()
    val authDisplayLiveData: LiveData<AuthViewDataModel> = _authDisplayLiveData

    private val requestService = RequestService()

    fun viewOpened() {
        _authDisplayLiveData.postValue(AuthViewDataModel.INITIAL_STATE)
    }

    fun handleClickingOnSignIn(login: String, password: String) {
        if ((login.isBlank()) || (password.isBlank())) {
            val showError = AuthViewDataModel.SHOW_ERROR
            showError.errorMessage = "Заполните все поля"
            _authDisplayLiveData.postValue(showError)
            return
        }
        requestService.auth(login, password, object : AuthResponseCallback {
            override fun onSuccess(response: Response<User>) {
                if (response.isSuccessful) {
                    val openMainMenu = AuthViewDataModel.OPEN_MAIN_MENU
                    openMainMenu.user = response.body()
                    _authDisplayLiveData.postValue(openMainMenu)
                } else {
                    val showError = AuthViewDataModel.SHOW_ERROR
                    showError.errorMessage = response.message()
                    _authDisplayLiveData.postValue(showError)
                }
            }

            override fun onFailure(throwable: Throwable) {
                val showError = AuthViewDataModel.SHOW_ERROR
                showError.errorMessage = throwable.message
                _authDisplayLiveData.postValue(showError)
            }
        })
    }
}