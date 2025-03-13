package com.example.yoga_app.backend.controllers;

import com.example.yoga_app.backend.entity.Schedule;
import com.example.yoga_app.backend.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // ✅ Get all schedules (Public)
    @GetMapping
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }
}
