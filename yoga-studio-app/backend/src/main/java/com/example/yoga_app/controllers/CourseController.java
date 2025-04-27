package com.example.yoga_app.controllers;

import com.example.yoga_app.dto.CourseDto;
import com.example.yoga_app.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @Operation(summary = "Get all courses", description = "Retrieve the list of all courses.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Courses retrieved successfully")
    })
    @GetMapping
    public List<CourseDto> getAllCourses() {
        return courseService.getAllCourses();
    }

    @Operation(summary = "Add a new course", description = "Create a new course (requires ADMIN or TEACHER role).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course added successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden - not authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<String> addCourse(@RequestBody CourseDto courseDto) {
        courseService.addCourseFromDto(courseDto);
        return ResponseEntity.ok("Course added successfully");
    }
}
