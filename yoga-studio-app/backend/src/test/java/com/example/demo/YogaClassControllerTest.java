package com.example.yoga_app.controllers;

import com.example.yoga_app.repository.YogaClassRepository;
import com.example.yoga_app.repository.LevelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(YogaClassController.class)
class YogaClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private YogaClassRepository yogaClassRepository;

    @MockBean
    private LevelRepository levelRepository;

    @Test
    void getAllYogaClasses_ReturnsOk() throws Exception {
        mockMvc.perform(get("/api/yoga-classes"))
                .andExpect(status().isOk());
    }
}
