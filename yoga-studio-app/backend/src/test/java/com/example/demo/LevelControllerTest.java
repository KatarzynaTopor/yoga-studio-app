package com.example.yoga_app.controllers;

import com.example.yoga_app.repository.LevelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LevelController.class)
class LevelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LevelRepository levelRepository;

    @Test
    void getAllLevels_ReturnsOk() throws Exception {
        mockMvc.perform(get("/api/levels"))
                .andExpect(status().isOk());
    }
}
