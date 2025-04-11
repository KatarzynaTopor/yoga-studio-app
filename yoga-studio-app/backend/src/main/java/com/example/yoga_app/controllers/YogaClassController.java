package com.example.yoga_app.controllers;

import com.example.yoga_app.entity.YogaClass;
import com.example.yoga_app.repository.YogaClassRepository;
import com.example.yoga_app.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/yoga-classes")
@RequiredArgsConstructor
public class YogaClassController {

    private final YogaClassRepository yogaClassRepository;
    private final LevelRepository levelRepository;

    @GetMapping
    public List<YogaClass> getAllYogaClasses() {
        return yogaClassRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> addYogaClass(@RequestBody YogaClass yogaClass) {
        if (yogaClass.getLevel() != null) {
            UUID levelId = yogaClass.getLevel().getId();
            Optional<?> level = levelRepository.findById(levelId);
            if (level.isEmpty()) {
                return ResponseEntity.badRequest().body("Level not found.");
            }
        }
        YogaClass saved = yogaClassRepository.save(yogaClass);
        return ResponseEntity.ok(saved);
    }
}
