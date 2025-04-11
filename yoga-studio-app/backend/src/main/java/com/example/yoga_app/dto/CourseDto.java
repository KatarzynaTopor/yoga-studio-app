package com.example.yoga_app.dto;

import com.example.yoga_app.entity.Course;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseDto {
    private String title;
    private String duration;
    private String level;
    private String instructor;
    private String yogaClass;

    public static CourseDto fromEntity(Course course) {
        return CourseDto.builder()
                .title(course.getTitle())
                .duration(course.getDuration())
                .level(course.getLevel().getName())
                .instructor(course.getInstructor().getName())
                .yogaClass(course.getYogaClass().getName())
                .build();
    }
}
