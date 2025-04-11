package com.example.yoga_app.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingDto {
    private UUID userId;
    private UUID scheduleId;
    private String notes;

    public BookingDto(UUID userId, UUID scheduleId, String notes) {
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.notes = notes;
    }
}
