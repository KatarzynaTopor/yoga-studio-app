package com.example.yoga_app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.yoga_app.entity.Instructor;
import com.example.yoga_app.repository.InstructorRepository;
import com.example.yoga_app.config.WebConfig;
import java.util.List;
import java.util.Map;
import com.example.yoga_app.dto.InstructorDto;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.io.IOException;
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Instructor> addInstructor(
            @RequestPart("instructor") Instructor instructor,
            @RequestPart("image") MultipartFile image
    ) {
        try {
            String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path imagePath = Paths.get("uploads", fileName);
            Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

            instructor.setImageUrl("/uploads/" + fileName);

            Instructor saved = instructorRepository.save(instructor);
            return ResponseEntity.ok(saved);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }




}