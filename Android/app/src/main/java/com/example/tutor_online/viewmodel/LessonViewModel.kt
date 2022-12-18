package com.example.tutor_online.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutor_online.datamodel.viewDataModel.LessonViewDataModel
import com.example.tutor_online.service.RequestService

class LessonViewModel: ViewModel(), IBaseViewModel {

    private val _lessonStateLiveData: MutableLiveData<LessonViewDataModel> = MutableLiveData()
    val lessonStateLiveData: LiveData<LessonViewDataModel> = _lessonStateLiveData

    private val requestService = RequestService()

    override fun viewOpened() {
        _lessonStateLiveData.postValue(LessonViewDataModel.INITIAL_STATE)
    }

    fun handleRequestButtonClick(userId: String, lessonId: String) {
        requestService.requestLesson(userId, lessonId)
    }
}