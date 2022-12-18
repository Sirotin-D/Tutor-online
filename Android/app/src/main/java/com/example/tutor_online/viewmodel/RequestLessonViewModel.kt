package com.example.tutor_online.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutor_online.datamodel.RequestLesson
import com.example.tutor_online.datamodel.RequestLessonStatus
import com.example.tutor_online.datamodel.UserType
import com.example.tutor_online.datamodel.viewDataModel.RequestLessonViewDataModel
import com.example.tutor_online.service.RequestService

class RequestLessonViewModel: ViewModel() {

    private val _requestLessonStateLiveData: MutableLiveData<RequestLessonViewDataModel> = MutableLiveData()
    val requestLessonStateLiveData: LiveData<RequestLessonViewDataModel> = _requestLessonStateLiveData

    private val requestService = RequestService()

    fun viewOpened(requestLesson: RequestLesson, currentUserType: String) {
        if ((currentUserType == UserType.STUDENT.name) && (requestLesson.request_status == RequestLessonStatus.PENDING.name)) {
            _requestLessonStateLiveData.postValue(RequestLessonViewDataModel.SHOW_PENDING_LESSON_FOR_STUDENT)
        } else if ((currentUserType == UserType.TUTOR.name) && (requestLesson.request_status == RequestLessonStatus.PENDING.name)) {
            _requestLessonStateLiveData.postValue(RequestLessonViewDataModel.SHOW_PENDING_LESSON_FOR_TUTOR)
        } else if (requestLesson.request_status == RequestLessonStatus.ACCEPTED.name) {
            _requestLessonStateLiveData.postValue(RequestLessonViewDataModel.SHOW_ACCEPTED_LESSON)
        } else if (requestLesson.request_status == RequestLessonStatus.CANCELLED.name) {
            _requestLessonStateLiveData.postValue(RequestLessonViewDataModel.SHOW_CANCELLED_LESSON)
        }
    }

    fun handleCancelButtonClick(userId: String, requestLessonId: String) {
        _requestLessonStateLiveData.postValue(RequestLessonViewDataModel.SHOW_LESSON_AFTER_BUTTON_PRESSED)
        requestService.cancelLesson(userId, requestLessonId)
    }

    fun handleAcceptButtonClick(userId: String,requestLessonId: String) {
        _requestLessonStateLiveData.postValue(RequestLessonViewDataModel.SHOW_LESSON_AFTER_BUTTON_PRESSED)
        requestService.acceptLesson(userId, requestLessonId)
    }

    fun handleDeleteButtonClick(userId: String, requestLessonId: String) {
        _requestLessonStateLiveData.postValue(RequestLessonViewDataModel.SHOW_LESSON_AFTER_BUTTON_PRESSED)
        requestService.deleteLesson(userId, requestLessonId)
    }
}