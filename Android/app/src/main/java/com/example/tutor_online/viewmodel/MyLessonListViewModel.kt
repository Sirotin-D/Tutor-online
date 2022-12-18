package com.example.tutor_online.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutor_online.datamodel.RequestLesson
import com.example.tutor_online.datamodel.viewdatamodel.LessonListViewDataModel
import com.example.tutor_online.service.requestservice.MyLessonListResponseCallback
import com.example.tutor_online.service.requestservice.RequestService
import retrofit2.Response

class MyLessonListViewModel: ViewModel() {
    private val _myLessonsListLiveData: MutableLiveData<List<RequestLesson>> = MutableLiveData()
    val myLessonsListLiveData: LiveData<List<RequestLesson>> = _myLessonsListLiveData

    private val _myLessonsListStateLiveData: MutableLiveData<LessonListViewDataModel> = MutableLiveData()
    val myLessonsListStateLiveData: LiveData<LessonListViewDataModel> = _myLessonsListStateLiveData

    private val requestService = RequestService()

    fun viewOpened(userId: Int) {
        _myLessonsListStateLiveData.postValue(LessonListViewDataModel.SHOW_LOADING)
        requestService.getMyLessonList(userId, object : MyLessonListResponseCallback {
            override fun onSuccess(response: Response<MutableList<RequestLesson>>) {
                if (response.isSuccessful) {
                    _myLessonsListLiveData.postValue(response.body())
                } else {
                    val showError = LessonListViewDataModel.SHOW_ERROR
                    showError.errorMessage = response.message()
                    _myLessonsListStateLiveData.postValue(showError)
                }
            }

            override fun onFailure(throwable: Throwable) {
                val showError = LessonListViewDataModel.SHOW_ERROR
                showError.errorMessage = throwable.message
                _myLessonsListStateLiveData.postValue(showError)
            }
        })
    }
}