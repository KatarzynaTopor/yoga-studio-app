package com.example.yoga_app.controllers;

import com.example.yoga_app.dto.ScheduleRequestDto;
import com.example.yoga_app.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ScheduleController.class)
class ScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleService scheduleService;

    @Test
    void createSchedule_ReturnsCreated() throws Exception {
        String json = """
            {
              "title": "Morning Yoga",
              "description": "Start your day with yoga",
              "instructorId": "00000000-0000-0000-0000-000000000001",
              "scheduleTime": "2025-05-01T10:00:00",
              "capacity": 20,
              "location": "Warsaw Studio",
              "room": "206",
              "duration": 60
            }
            """;

        mockMvc.perform(post("/api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void createSchedule_InvalidRequest_ReturnsBadRequest() throws Exception {
        // brakujące pola
        String json = """
            {
              "title": "Invalid"
            }
            """;

        mockMvc.perform(post("/api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteSchedule_ReturnsNoContent() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/api/schedule/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteNonExistingSchedule_ReturnsNotFound() throws Exception {
        UUID nonExistingId = UUID.randomUUID();

        doThrow(new RuntimeException("Schedule not found"))
                .when(scheduleService).deleteSchedule(nonExistingId);

        mockMvc.perform(delete("/api/schedule/" + nonExistingId))
                .andExpect(status().isNotFound());
    }
}
