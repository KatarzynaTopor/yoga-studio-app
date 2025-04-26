package com.example.yoga_app.controllers;

import com.example.yoga_app.service.BookingService;
import com.example.yoga_app.repository.BookingRepository;
import com.example.yoga_app.repository.ScheduleRepository;
import com.example.yoga_app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingController.class)
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @MockBean
    private BookingRepository bookingRepository;

    @MockBean
    private ScheduleRepository scheduleRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    void bookFullClass_ReturnsBadRequest() throws Exception {
        UUID scheduleId = UUID.randomUUID();

        doThrow(new RuntimeException("Class is full"))
                .when(bookingService).getBookingsByTeacherId(scheduleId);

        mockMvc.perform(post("/api/schedule/" + scheduleId + "/book"))
                .andExpect(status().isBadRequest());
    }
}
