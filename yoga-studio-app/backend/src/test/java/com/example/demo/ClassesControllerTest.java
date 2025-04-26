package com.example.yoga_app.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClassesController.class)
class ClassesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllClasses_ReturnsOk() throws Exception {
        mockMvc.perform(get("/api/classes"))
                .andExpect(status().isOk());
    }
}
