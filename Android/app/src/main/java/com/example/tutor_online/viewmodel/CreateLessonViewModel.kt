package com.example.tutor_online.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutor_online.datamodel.User
import com.example.tutor_online.datamodel.viewdatamodel.CreateLessonViewDataModel
import com.example.tutor_online.service.requestservice.CreateLessonResponseCallback
import com.example.tutor_online.service.requestservice.RequestService

class CreateLessonViewModel: ViewModel() {

    private val _createLessonStateLiveData: MutableLiveData<CreateLessonViewDataModel> = MutableLiveData()
    val createLessonStateLiveData: LiveData<CreateLessonViewDataModel> = _createLessonStateLiveData

    private val requestService = RequestService()

    fun viewOpened() {
        _createLessonStateLiveData.postValue(CreateLessonViewDataModel.INITIAL_STATE)
    }

    fun createLessonButtonPressed(tutor: User, lessonTitle: String, lessonDescription: String, lessonContactData: String) {
        if ((lessonTitle != "") && (lessonDescription != "") && (lessonContactData != "")) {
            requestService.createLesson(tutor.id, tutor.name, lessonTitle,lessonContactData, lessonDescription, object :
                CreateLessonResponseCallback {
                override fun onSuccess() {
                    _createLessonStateLiveData.postValue(CreateLessonViewDataModel.LESSON_CREATED)
                }

                override fun onFailure(throwable: Throwable) {
                    val error = CreateLessonViewDataModel.SHOW_ERROR
                    error.errorMessage = throwable.message
                    _createLessonStateLiveData.postValue(error)
                }
            })
        }
    }
}