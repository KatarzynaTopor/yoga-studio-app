package com.example.yoga_app.dto;

import java.util.UUID;

public record UserProfileDto(
        UUID id,
        String username,
        String email
) {}
