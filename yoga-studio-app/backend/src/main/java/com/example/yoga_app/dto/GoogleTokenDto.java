package com.example.yoga_app.dto;

import jakarta.validation.constraints.NotBlank;

public record GoogleTokenDto(
        @NotBlank(message = "Google ID Token is required")
        String idToken
) {
}
