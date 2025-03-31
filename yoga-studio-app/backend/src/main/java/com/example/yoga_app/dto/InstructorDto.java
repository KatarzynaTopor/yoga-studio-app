package com.example.yoga_app.dto;
import java.util.UUID;

public record InstructorDto(
        UUID id,
        String name,
        String specialties,
        String experience,
        String bio,
        String imageUrl
) {}
