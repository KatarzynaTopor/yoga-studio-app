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
                Map.of("name", "dldkfe", "bio", "oekfoek"),
                Map.of("name", "feof", "bio", "ekfe"),
                Map.of("name", "fkr", "bio", "fkrlkf")
        );

        return ResponseEntity.ok(instructors);
    }
}
