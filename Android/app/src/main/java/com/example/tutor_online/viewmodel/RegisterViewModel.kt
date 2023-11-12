package com.example.tutor_online.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutor_online.datamodel.User
import com.example.tutor_online.datamodel.UserType
import com.example.tutor_online.datamodel.viewdatamodel.RegisterViewDataModel
import com.example.tutor_online.service.requestservice.CreateAccountResponseCallback
import com.example.tutor_online.service.requestservice.RequestService
import retrofit2.Response

class RegisterViewModel: ViewModel() {
    private var _registerStateLiveData: MutableLiveData<RegisterViewDataModel> = MutableLiveData()
    val registerStateLiveData: LiveData<RegisterViewDataModel> = _registerStateLiveData

    private val requestService = RequestService()

    fun viewOpened() {
        _registerStateLiveData.postValue(RegisterViewDataModel.INITIAL_STATE)
    }

    fun registerButtonPressed(name: String, age: Int, phone: String, email: String, password: String, isTutor: Boolean) {
        if ((name != "") && (phone != "") && (email != "") && (password != "")) {
            val userType = if (isTutor) {
                UserType.TUTOR.name
            } else {
                UserType.STUDENT.name
            }
            requestService.createAccount(name, age, phone, email, password, userType, object :
                CreateAccountResponseCallback {
                override fun onSuccess(response: Response<User>) {
                    if (response.isSuccessful) {
                        val openMainMenu = RegisterViewDataModel.OPEN_MAIN_MENU
                        openMainMenu.user = response.body()
                        _registerStateLiveData.postValue(openMainMenu)
                    } else {
                        val showError = RegisterViewDataModel.SHOW_ERROR
                        showError.errorMessage = response.message()
                        _registerStateLiveData.postValue(showError)
                    }
                }

                override fun onFailure(throwable: Throwable) {
                    val showError = RegisterViewDataModel.SHOW_ERROR
                    showError.errorMessage = throwable.message
                    _registerStateLiveData.postValue(showError)
                }
            })
        } else {
            val showError = RegisterViewDataModel.SHOW_ERROR
            showError.errorMessage = "Заполните все поля"
            _registerStateLiveData.postValue(showError)
        }
    }
}