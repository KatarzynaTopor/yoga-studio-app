package com.example.yoga_app.service;

import com.example.yoga_app.entity.Course;
import com.example.yoga_app.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    void shouldReturnAllCourses() {
        Course course = new Course();
        course.setTitle("Yoga Course");

        when(courseRepository.findAll()).thenReturn(List.of(course));

        var courses = courseService.getAllCourses();

        assertEquals(1, courses.size());
        assertEquals("Yoga Course", courses.get(0).getTitle());
    }
}
