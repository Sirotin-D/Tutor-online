package com.example.tutor_online.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutor_online.datamodel.viewDataModel.RequestLessonViewDataModel
import com.example.tutor_online.service.RequestService

class RequestLessonViewModel: ViewModel(), IBaseViewModel {

    private val _requestLessonStateLiveData: MutableLiveData<RequestLessonViewDataModel> = MutableLiveData()
    val requestLessonStateLiveData: LiveData<RequestLessonViewDataModel> = _requestLessonStateLiveData

    private val requestService = RequestService()

    override fun viewOpened() {
        _requestLessonStateLiveData.postValue(RequestLessonViewDataModel.INITIAL_STATE)
    }

    fun handleCancelButtonClick(userId: String, requestLessonId: String) {
        requestService.cancelLesson(userId, requestLessonId)
    }
    fun handleAcceptButtonClick(userId: String,requestLessonId: String) {
        requestService.acceptLesson(userId, requestLessonId)
    }

    fun handleDeleteButtonClick(userId: String, requestLessonId: String) {
        requestService.deleteLesson(userId, requestLessonId)
    }
}