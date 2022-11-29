package com.mentor.backwebmentor.service

import com.mentor.backwebmentor.dto.CoursesDto

interface CoursesService {

    fun getAll(): List<CoursesDto>
}