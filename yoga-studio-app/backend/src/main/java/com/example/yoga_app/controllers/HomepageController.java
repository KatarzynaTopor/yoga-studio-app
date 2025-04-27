package com.example.yoga_app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/api/homepage")
public class HomepageController {

    @Operation(summary = "Homepage message", description = "Simple welcome message for Yoga Studio API.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Homepage message retrieved")
    })
    @GetMapping
    public ResponseEntity<String> getHomepage() {
        return ResponseEntity.ok("Welcome to the Yoga Studio API!");
    }
}
