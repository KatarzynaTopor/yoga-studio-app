package com.example.yoga_app.controllers;

import com.example.yoga_app.dto.RegistrationRequestDto;
import com.example.yoga_app.dto.RegistrationResponseDto;
import com.example.yoga_app.service.UserRegistrationService;
import com.example.yoga_app.mapper.UserRegistrationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRegistrationService userRegistrationService;

    @MockBean
    private UserRegistrationMapper userRegistrationMapper;

    @Test
    void registerUser_ReturnsOk() throws Exception {
        RegistrationRequestDto request = new RegistrationRequestDto("testuser", "test@example.com", "password");
        RegistrationResponseDto response = new RegistrationResponseDto("testuser", "test@example.com");

        // Mocking
        when(userRegistrationService.registerUser(userRegistrationMapper.toEntity(request)))
                .thenReturn(userRegistrationMapper.toEntity(request));
        when(userRegistrationMapper.toRegistrationResponseDto(userRegistrationMapper.toEntity(request)))
                .thenReturn(response);

        mockMvc.perform(post("/api/auth/register")
                        .contentType("application/json")
                        .content("""
                        {
                          "username": "testuser",
                          "email": "test@example.com",
                          "password": "password"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"));
    }
}
