package com.example.yoga_app.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;


import java.time.LocalDateTime;

public record ScheduleRequestDto(
        @NotBlank String title,
        @NotBlank String description,
        @NotBlank String instructor,
        @NotNull @Future LocalDateTime scheduleTime,
        @Min(1) int capacity
) {}
