package com.example.yoga_app.mapper;

import com.example.yoga_app.dto.InstructorDto;
import com.example.yoga_app.entity.Instructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


public class InstructorMapper {
    public static InstructorDto toDto(Instructor i) {
        return new InstructorDto(
                i.getId(),
                i.getName(),
                i.getSpecialties(),
                i.getExperience(),
                i.getBio(),
                i.getImageUrl()
        );
    }
}
