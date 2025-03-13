package com.example.yoga_app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class ClassesController {

    @GetMapping
    public ResponseEntity<List<Map<String, String>>> getAllClasses() {
        List<Map<String, String>> classes = List.of(
                Map.of("title", "Morning Yoga", "description", "A relaxing morning session."),
                Map.of("title", "Power Yoga", "description", "High-intensity session to build strength."),
                Map.of("title", "Evening Meditation", "description", "A guided meditation session.")
        );

        return ResponseEntity.ok(classes);
    }
}
