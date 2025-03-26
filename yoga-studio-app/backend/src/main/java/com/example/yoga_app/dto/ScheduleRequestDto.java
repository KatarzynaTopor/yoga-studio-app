package com.example.yoga_app.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import java.time.LocalDateTime;

public record ScheduleRequestDto(
        @NotBlank String title,
        @NotBlank String description,
        @NotNull UUID instructorId,
        @NotNull @Future LocalDateTime scheduleTime,
        @Min(1) int capacity,
        @NotBlank String location,
        @NotBlank String room,
        @Min(1) int duration
) {}