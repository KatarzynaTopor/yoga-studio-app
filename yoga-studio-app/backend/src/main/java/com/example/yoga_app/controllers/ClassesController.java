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
                Map.of("id", "1", "name", "Yoga for Beginners", "description", "Introductory class"),
                Map.of("id", "2", "name", "Power Yoga", "description", "Intense yoga session"),
                Map.of("id", "3", "name", "Relaxation Yoga", "description", "Calm and soothing practice")
        );

        return ResponseEntity.ok(classes);
    }
}
