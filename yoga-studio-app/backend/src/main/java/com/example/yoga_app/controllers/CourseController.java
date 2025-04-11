package com.example.yoga_app.controllers;

import com.example.yoga_app.dto.CourseDto;
import com.example.yoga_app.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<CourseDto> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping
    public ResponseEntity<String> addCourse(@RequestBody CourseDto courseDto) {
        courseService.addCourseFromDto(courseDto);
        return ResponseEntity.ok("Course added successfully");
    }
}
