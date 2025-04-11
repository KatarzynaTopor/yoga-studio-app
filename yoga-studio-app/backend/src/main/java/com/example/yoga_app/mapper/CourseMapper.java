package com.example.yoga_app.mapper;

import com.example.yoga_app.dto.CourseDto;
import com.example.yoga_app.entity.Course;

public class CourseMapper {
    public static CourseDto toDto(Course course) {
        return CourseDto.builder()
                .title(course.getTitle())
                .duration(course.getDuration())
                .level(course.getLevel().getName())
                .instructor(course.getInstructor().getName())
                .yogaClass(course.getYogaClass().getName())
                .build();
    }
}
