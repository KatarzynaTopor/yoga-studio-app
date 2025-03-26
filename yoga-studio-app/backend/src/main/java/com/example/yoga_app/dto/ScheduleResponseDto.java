package com.example.yoga_app.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ScheduleResponseDto(
        UUID id,
        String title,
        String description,
        LocalDateTime scheduleTime,
        int capacity,
        String instructorName,
        String location,
        String room,
        int duration
) {}