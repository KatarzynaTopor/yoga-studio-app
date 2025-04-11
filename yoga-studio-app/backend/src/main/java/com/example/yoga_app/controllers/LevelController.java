package com.example.yoga_app.controllers;

import com.example.yoga_app.entity.Level;
import com.example.yoga_app.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/levels")
@RequiredArgsConstructor
public class LevelController {

    private final LevelRepository levelRepository;

    @GetMapping
    public List<Level> getAllLevels() {
        return levelRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Level> addLevel(@RequestBody Level level) {
        Level saved = levelRepository.save(level);
        return ResponseEntity.ok(saved);
    }
}
