package com.example.tutor_online.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutor_online.datamodel.viewdatamodel.LessonViewDataModel
import com.example.tutor_online.service.requestservice.CreateRequestLessonResponseCallback
import com.example.tutor_online.service.requestservice.RequestService

class LessonViewModel: ViewModel(), IBaseViewModel {

    private val _lessonStateLiveData: MutableLiveData<LessonViewDataModel> = MutableLiveData()
    val lessonStateLiveData: LiveData<LessonViewDataModel> = _lessonStateLiveData

    private val requestService = RequestService()

    override fun viewOpened() {
        _lessonStateLiveData.postValue(LessonViewDataModel.INITIAL_STATE)
    }

    fun handleRequestButtonClick(userId: Int, lessonId: Int) {
        requestService.requestLesson(userId, lessonId, object :
            CreateRequestLessonResponseCallback {
            override fun onSuccess() {
                _lessonStateLiveData.postValue(LessonViewDataModel.LESSON_REQUESTED)
            }

            override fun onFailure(throwable: Throwable) {
                val showError = LessonViewDataModel.SHOW_ERROR
                showError.errorMessage = throwable.message
                _lessonStateLiveData.postValue(showError)
            }
        })
    }
}