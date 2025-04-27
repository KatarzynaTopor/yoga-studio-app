package com.example.yoga_app.controllers;

import com.example.yoga_app.entity.Level;
import com.example.yoga_app.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import java.util.List;

@RestController
@RequestMapping("/api/levels")
@RequiredArgsConstructor
public class LevelController {

    private final LevelRepository levelRepository;

    @Operation(summary = "Get all levels", description = "Retrieve the list of all yoga levels.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Levels retrieved successfully")
    })
    @GetMapping
    public List<Level> getAllLevels() {
        return levelRepository.findAll();
    }

    @Operation(summary = "Add a new level", description = "Create a new yoga level (requires ADMIN role).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Level added successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden - not authorized"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Level> addLevel(@RequestBody Level level) {
        Level saved = levelRepository.save(level);
        return ResponseEntity.ok(saved);
    }
}
