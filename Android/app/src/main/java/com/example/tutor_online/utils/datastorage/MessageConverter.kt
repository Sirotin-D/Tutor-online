package com.example.tutor_online.utils.datastorage

import com.example.tutor_online.datamodel.RequestLessonStatus
import com.example.tutor_online.datamodel.UserType

object MessageConverter {

    fun getStringForRequestLessonStatus(requestLessonStatus: String): String {
        var lessonStatusString: String = ""
        when (requestLessonStatus) {
            RequestLessonStatus.PENDING.name -> {
                lessonStatusString = RequestLessonStatus.PENDING.toString()
            }
            RequestLessonStatus.ACCEPTED.name -> {
                lessonStatusString = RequestLessonStatus.ACCEPTED.toString()
            }
            RequestLessonStatus.CANCELLED.name -> {
                lessonStatusString = RequestLessonStatus.CANCELLED.toString()
            }
            RequestLessonStatus.DELETED.name -> {
                lessonStatusString = RequestLessonStatus.DELETED.toString()
            }
        }
        return lessonStatusString
    }

    fun getStringForUserStatus(userStatus: String): String {
        var lessonStatusString: String = ""
        if (userStatus == UserType.TUTOR.name){
            lessonStatusString = UserType.TUTOR.toString()
        } else if (userStatus == UserType.STUDENT.name) {
            lessonStatusString = UserType.STUDENT.toString()
        }
        return lessonStatusString
    }
}