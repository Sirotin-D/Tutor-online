package com.mentor.backwebmentor.controller

import com.mentor.backwebmentor.dto.CoursesDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.mentor.backwebmentor.service.CoursesService

@RestController
@RequestMapping("/courses")
class CoursesController(
    private val coursesService: CoursesService,
    ) {
    @GetMapping
    fun getAll(): List<CoursesDto> = coursesService.getAll()
}