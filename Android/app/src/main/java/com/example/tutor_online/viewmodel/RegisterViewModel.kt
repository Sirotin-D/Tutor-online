package com.example.tutor_online.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutor_online.datamodel.UserType
import com.example.tutor_online.datamodel.viewDataModel.RegisterViewDataModel
import com.example.tutor_online.service.RequestService

class RegisterViewModel: ViewModel() {
    private var _registerStateLiveData: MutableLiveData<RegisterViewDataModel> = MutableLiveData()
    val registerStateLiveData: LiveData<RegisterViewDataModel> = _registerStateLiveData

    private val requestService = RequestService()

    fun viewOpened() {
        _registerStateLiveData.postValue(RegisterViewDataModel.INITIAL_STATE)
    }

    fun registerButtonPressed(name: String, age: String, phone: String, email: String, password: String, isTutor: Boolean) {
        if ((name != "") && (age != "") && (phone != "") && (email != "") && (password != "")) {
            val userType: String = if (isTutor) {
                UserType.TUTOR.name
            } else {
                UserType.STUDENT.name
            }
            val user = requestService.createAccount(name, age, phone, email, password, userType)
            val endRegisterState = RegisterViewDataModel.END_REGISTER
            endRegisterState.user = user
            _registerStateLiveData.postValue(endRegisterState)
        }
    }
}