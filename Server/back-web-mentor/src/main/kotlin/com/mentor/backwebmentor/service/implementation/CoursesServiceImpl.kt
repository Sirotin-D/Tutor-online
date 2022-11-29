package com.mentor.backwebmentor.service.implementation

import com.mentor.backwebmentor.dto.CoursesDto
import org.springframework.stereotype.Service
import com.mentor.backwebmentor.service.CoursesService

@Service
class CoursesServiceImpl : CoursesService {
    override fun getAll(): List<CoursesDto> {
        return listOf(
            CoursesDto(1, "Computer Science for Beginners", "Programming", 14 ),
            CoursesDto(2, "Basic C++", "Programming", 21 ),
            CoursesDto(2, "Simple knitting", "Hand-Made", 10 ),
        )
    }
}