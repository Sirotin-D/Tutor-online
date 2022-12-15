package com.example.tutor_online.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutor_online.datamodel.Lesson
import com.example.tutor_online.datamodel.viewDataModel.LessonListViewDataModel
import com.example.tutor_online.service.RequestService

class LessonListViewModel: ViewModel(), IBaseViewModel {
    private val _lessonsListLiveData: MutableLiveData<List<Lesson>> = MutableLiveData()
    val lessonsListLiveData: LiveData<List<Lesson>> = _lessonsListLiveData

    private val _lessonsListStateLiveData: MutableLiveData<LessonListViewDataModel> = MutableLiveData()
    val lessonsListStateLiveData: LiveData<LessonListViewDataModel> = _lessonsListStateLiveData

    private val requestService = RequestService()

    override fun viewOpened() {
        _lessonsListStateLiveData.postValue(LessonListViewDataModel.SHOW_LOADING)
       val lessonList = requestService.getLessonList()
        _lessonsListLiveData.postValue(lessonList)
        _lessonsListStateLiveData.postValue(LessonListViewDataModel.HIDE_LOADING)
    }
}