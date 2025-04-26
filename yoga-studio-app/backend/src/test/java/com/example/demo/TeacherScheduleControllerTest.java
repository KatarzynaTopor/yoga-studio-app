package com.example.yoga_app.controllers;

import com.example.yoga_app.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeacherScheduleController.class)
class TeacherScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingRepository bookingRepository;

    @Test
    void getEnrolledStudents_ReturnsForbiddenWithoutAuth() throws Exception {
        mockMvc.perform(get("/api/teacher/schedule/{id}/students", "dummy-id"))
                .andExpect(status().isForbidden()); // needs Teacher role
    }
}
