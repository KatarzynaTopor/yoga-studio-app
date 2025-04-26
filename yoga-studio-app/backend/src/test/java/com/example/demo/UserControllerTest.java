package com.example.yoga_app.controllers;

import com.example.yoga_app.repository.UserRepository;
import com.example.yoga_app.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BookingService bookingService;

    @Test
    void getUserBookings_Unauthorized_ReturnsForbidden() throws Exception {
        mockMvc.perform(get("/api/users/{id}", "dummy-id"))
                .andExpect(status().isForbidden());
    }
}
