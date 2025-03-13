package com.example.yoga_app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/instructors")
@RequiredArgsConstructor
public class InstructorsController {

    @GetMapping
    public ResponseEntity<List<Map<String, String>>> getAllInstructors() {
        List<Map<String, String>> instructors = List.of(
                Map.of("name", "Alice Johnson", "bio", "Expert in Hatha and Vinyasa yoga."),
                Map.of("name", "Bob Smith", "bio", "Specialist in Power Yoga and Meditation."),
                Map.of("name", "Clara Williams", "bio", "Over 10 years of experience in mindfulness and relaxation techniques.")
        );

        return ResponseEntity.ok(instructors);
    }
}
