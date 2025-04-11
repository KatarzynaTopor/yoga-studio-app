package com.example.yoga_app.service;

import com.example.yoga_app.dto.CourseDto;
import com.example.yoga_app.entity.Course;
import com.example.yoga_app.entity.Instructor;
import com.example.yoga_app.entity.Level;
import com.example.yoga_app.entity.YogaClass;
import com.example.yoga_app.repository.CourseRepository;
import com.example.yoga_app.repository.InstructorRepository;
import com.example.yoga_app.repository.LevelRepository;
import com.example.yoga_app.repository.YogaClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final LevelRepository levelRepository;
    private final YogaClassRepository yogaClassRepository;

    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(CourseDto::fromEntity)
                .toList();
    }

    public void addCourseFromDto(CourseDto dto) {
        Instructor instructor = instructorRepository.findByName(dto.getInstructor())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        Level level = levelRepository.findByName(dto.getLevel())
                .orElseThrow(() -> new RuntimeException("Level not found"));

        YogaClass yogaClass = yogaClassRepository.findByName(dto.getYogaClass())
                .orElseThrow(() -> new RuntimeException("Yoga class not found"));

        Course course = new Course();
        course.setTitle(dto.getTitle());
        course.setDuration(dto.getDuration());
        course.setInstructor(instructor);
        course.setLevel(level);
        course.setYogaClass(yogaClass);

        courseRepository.save(course);
    }
}
