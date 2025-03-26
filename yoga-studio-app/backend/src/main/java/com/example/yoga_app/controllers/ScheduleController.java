package com.example.yoga_app.controllers;

import com.example.yoga_app.dto.ScheduleRequestDto;
import com.example.yoga_app.dto.ScheduleResponseDto;
import com.example.yoga_app.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    @PostMapping
    public ResponseEntity<Void> createSchedule(@Valid @RequestBody ScheduleRequestDto dto) {
        scheduleService.createSchedule(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
