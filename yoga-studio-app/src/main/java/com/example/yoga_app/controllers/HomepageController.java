package com.example.yoga_app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/homepage")
public class HomepageController {

    @GetMapping
    public ResponseEntity<String> getHomepage() {
        return ResponseEntity.ok("Welcome to the Yoga Studio API!");
    }
}
