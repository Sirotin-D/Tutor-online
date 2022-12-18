package com.example.tutor_online.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutor_online.datamodel.viewDataModel.CreateLessonViewDataModel
import com.example.tutor_online.service.RequestService

class CreateLessonViewModel: ViewModel() {

    private val _createLessonStateLiveData: MutableLiveData<CreateLessonViewDataModel> = MutableLiveData()
    val createLessonStateLiveData: LiveData<CreateLessonViewDataModel> = _createLessonStateLiveData

    private val requestService = RequestService()

    fun viewOpened() {
        _createLessonStateLiveData.postValue(CreateLessonViewDataModel.INITIAL_STATE)
    }

    fun createLessonButtonPressed(tutorId: String, lessonTitle: String, lessonDescription: String, lessonContactData: String) {
        if ((lessonTitle != "") && (lessonDescription != "") && (lessonContactData != "")) {
            requestService.createLesson(tutorId, lessonTitle, lessonDescription, lessonContactData)
            _createLessonStateLiveData.postValue(CreateLessonViewDataModel.LESSON_CREATED)
        }
    }
}