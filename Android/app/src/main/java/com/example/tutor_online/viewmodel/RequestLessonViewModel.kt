package com.example.tutor_online.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutor_online.datamodel.Lesson
import com.example.tutor_online.datamodel.RequestLesson
import com.example.tutor_online.datamodel.RequestLessonStatus
import com.example.tutor_online.datamodel.UserType
import com.example.tutor_online.datamodel.viewdatamodel.RequestLessonViewDataModel
import com.example.tutor_online.service.requestservice.CurrentLessonResponseCallback
import com.example.tutor_online.service.requestservice.RequestService
import com.example.tutor_online.service.requestservice.UpdateRequestLessonStatusResponseCallback
import retrofit2.Response

class RequestLessonViewModel: ViewModel() {

    private val _requestLessonStateLiveData: MutableLiveData<RequestLessonViewDataModel> = MutableLiveData()
    val requestLessonStateLiveData: LiveData<RequestLessonViewDataModel> = _requestLessonStateLiveData

    private val requestService = RequestService()

    fun viewOpened(requestLesson: RequestLesson, currentUserType: String) {
        requestService.getCurrentLesson(requestLesson.lessonId, object :
            CurrentLessonResponseCallback {
            override fun onSuccess(response: Response<Lesson>) {
                if (response.isSuccessful) {
                    setLesson(currentUserType, requestLesson, response.body()!!)
                } else {
                    val showErrorState = RequestLessonViewDataModel.SHOW_ERROR
                    showErrorState.errorMessage = response.message()
                    _requestLessonStateLiveData.postValue(showErrorState)
                }
            }

            override fun onFailure(throwable: Throwable) {
                val showErrorState = RequestLessonViewDataModel.SHOW_ERROR
                showErrorState.errorMessage = throwable.message
                _requestLessonStateLiveData.postValue(showErrorState)
            }
        })
    }

    fun handleCancelButtonClick(requestLessonId: Int) {
        requestService.updateRequestLessonStatus(requestLessonId, RequestLessonStatus.CANCELLED, object :
            UpdateRequestLessonStatusResponseCallback {
            override fun onSuccess() {
                _requestLessonStateLiveData.postValue(RequestLessonViewDataModel.SHOW_LESSON_AFTER_BUTTON_PRESSED)
            }

            override fun onFailure(throwable: Throwable) {
                val showErrorState = RequestLessonViewDataModel.SHOW_ERROR
                showErrorState.errorMessage = throwable.message
                _requestLessonStateLiveData.postValue(showErrorState)
            }

        })
    }

    fun handleAcceptButtonClick(requestLessonId: Int) {
        _requestLessonStateLiveData.postValue(RequestLessonViewDataModel.SHOW_LESSON_AFTER_BUTTON_PRESSED)
        requestService.updateRequestLessonStatus(requestLessonId, RequestLessonStatus.ACCEPTED, object :
            UpdateRequestLessonStatusResponseCallback {
            override fun onSuccess() {
                _requestLessonStateLiveData.postValue(RequestLessonViewDataModel.SHOW_LESSON_AFTER_BUTTON_PRESSED)
            }

            override fun onFailure(throwable: Throwable) {
                val showErrorState = RequestLessonViewDataModel.SHOW_ERROR
                showErrorState.errorMessage = throwable.message
                _requestLessonStateLiveData.postValue(showErrorState)
            }

        })
    }

    fun handleDeleteButtonClick(requestLessonId: Int) {
        _requestLessonStateLiveData.postValue(RequestLessonViewDataModel.SHOW_LESSON_AFTER_BUTTON_PRESSED)
        requestService.updateRequestLessonStatus(requestLessonId, RequestLessonStatus.DELETED, object :
            UpdateRequestLessonStatusResponseCallback {
            override fun onSuccess() {
                _requestLessonStateLiveData.postValue(RequestLessonViewDataModel.SHOW_LESSON_AFTER_BUTTON_PRESSED)
            }

            override fun onFailure(throwable: Throwable) {
                val showErrorState = RequestLessonViewDataModel.SHOW_ERROR
                showErrorState.errorMessage = throwable.message
                _requestLessonStateLiveData.postValue(showErrorState)
            }

        })
    }

    fun setLesson(currentUserType: String, requestLesson: RequestLesson, currentLesson: Lesson) {
        var requestLessonState = RequestLessonViewDataModel.SHOW_ERROR
        if ((currentUserType == UserType.STUDENT.name) && (requestLesson.status == RequestLessonStatus.PENDING.name)) {
            requestLessonState = RequestLessonViewDataModel.SHOW_PENDING_LESSON_FOR_STUDENT
        } else if ((currentUserType == UserType.TUTOR.name) && (requestLesson.status == RequestLessonStatus.PENDING.name)) {
            requestLessonState = RequestLessonViewDataModel.SHOW_PENDING_LESSON_FOR_TUTOR
        } else if (requestLesson.status == RequestLessonStatus.ACCEPTED.name) {
            requestLessonState = RequestLessonViewDataModel.SHOW_ACCEPTED_LESSON
        } else if (requestLesson.status == RequestLessonStatus.CANCELLED.name) {
            requestLessonState = RequestLessonViewDataModel.SHOW_CANCELLED_LESSON
        }
        requestLessonState.lesson = currentLesson
        _requestLessonStateLiveData.postValue(requestLessonState)
    }
}