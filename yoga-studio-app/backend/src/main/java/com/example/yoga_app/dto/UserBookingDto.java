package com.example.yoga_app.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserBookingDto(
        UUID id,
        String title,
        LocalDateTime scheduleTime,
        String instructorName,
        String location,
        String room,
        int duration
) {}
