package com.example.yoga_app.controllers;

import com.example.yoga_app.entity.Schedule;
import com.example.yoga_app.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    // ✅ Get schedule details by ID (Public)
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable UUID id) {
        return ResponseEntity.ok(scheduleService.getScheduleById(id));
    }
}
