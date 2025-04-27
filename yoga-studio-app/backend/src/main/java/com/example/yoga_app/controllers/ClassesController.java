package com.example.yoga_app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;



import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class ClassesController {

    @Operation(summary = "Get all classes", description = "Retrieve the list of available yoga class types.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classes retrieved successfully")
    })
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
