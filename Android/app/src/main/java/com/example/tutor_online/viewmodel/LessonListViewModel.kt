package com.example.tutor_online.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutor_online.datamodel.Lesson
import com.example.tutor_online.datamodel.viewdatamodel.LessonListViewDataModel
import com.example.tutor_online.service.requestservice.LessonListResponseCallback
import com.example.tutor_online.service.requestservice.RequestService
import retrofit2.Response

class LessonListViewModel: ViewModel(), IBaseViewModel {
    private val _lessonsListLiveData: MutableLiveData<List<Lesson>> = MutableLiveData()
    val lessonsListLiveData: LiveData<List<Lesson>> = _lessonsListLiveData

    private val _lessonsListStateLiveData: MutableLiveData<LessonListViewDataModel> = MutableLiveData()
    val lessonsListStateLiveData: LiveData<LessonListViewDataModel> = _lessonsListStateLiveData

    private val requestService = RequestService()

    override fun viewOpened() {
        _lessonsListStateLiveData.postValue(LessonListViewDataModel.SHOW_LOADING)
       requestService.getLessonList(object : LessonListResponseCallback {
           override fun onSuccess(response: Response<MutableList<Lesson>>) {
               if (response.isSuccessful) {
                   _lessonsListLiveData.postValue(response.body())
               } else {
                   val showError = LessonListViewDataModel.SHOW_ERROR
                   showError.errorMessage = response.message()
                   _lessonsListStateLiveData.postValue(showError)
               }
           }

           override fun onFailure(throwable: Throwable) {
               Log.d("LessonListViewModel", "ERROR lesson list: $throwable")
           }
       })

        _lessonsListStateLiveData.postValue(LessonListViewDataModel.HIDE_LOADING)
    }
}