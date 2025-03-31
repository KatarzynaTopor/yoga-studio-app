package com.example.yoga_app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.yoga_app.entity.Instructor;
import com.example.yoga_app.repository.InstructorRepository;
import java.util.List;
import java.util.Map;
import com.example.yoga_app.dto.InstructorDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/instructors")
public class InstructorsController {

    private final InstructorRepository instructorRepository;

    @GetMapping
    public ResponseEntity<List<InstructorDto>> getAllInstructors() {
        List<InstructorDto> result = instructorRepository.findAll().stream()
                .map(i -> new InstructorDto(
                        i.getId(),
                        i.getName(),
                        i.getSpecialties(),
                        i.getExperience(),
                        i.getBio(),
                        i.getImageUrl()
                ))
                .toList();
        return ResponseEntity.ok(result);
    }


}