package com.example.yoga_app.dto;


import java.util.UUID;

public record AuthenticationResponseDto(
        String token,
        UUID userId,
        String username
) {}
