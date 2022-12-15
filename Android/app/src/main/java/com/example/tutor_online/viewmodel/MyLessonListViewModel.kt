package com.example.tutor_online.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutor_online.datamodel.Lesson
import com.example.tutor_online.datamodel.viewDataModel.LessonListViewDataModel
import com.example.tutor_online.service.RequestService

class MyLessonListViewModel: ViewModel(), IBaseViewModel {
    private val _myLessonsListLiveData: MutableLiveData<List<Lesson>> = MutableLiveData()
    val myLessonsListLiveData: LiveData<List<Lesson>> = _myLessonsListLiveData

    private val _myLessonsListStateLiveData: MutableLiveData<LessonListViewDataModel> = MutableLiveData()
    val myLessonsListStateLiveData: LiveData<LessonListViewDataModel> = _myLessonsListStateLiveData

    private val requestService = RequestService()

    override fun viewOpened() {
        _myLessonsListStateLiveData.postValue(LessonListViewDataModel.SHOW_LOADING)
        val lessonList = requestService.getMyLessonList()
        _myLessonsListLiveData.postValue(lessonList)
        _myLessonsListStateLiveData.postValue(LessonListViewDataModel.HIDE_LOADING)
    }
}