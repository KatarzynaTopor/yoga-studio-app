package com.example.yoga_app.controllers;

import com.example.yoga_app.entity.Class;
import com.example.yoga_app.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class ClassesController {

    private final ClassService classService;

    @GetMapping
    public ResponseEntity<List<Class>> getAllClasses() {
        return ResponseEntity.ok(classService.getAllClasses());
    }
}
