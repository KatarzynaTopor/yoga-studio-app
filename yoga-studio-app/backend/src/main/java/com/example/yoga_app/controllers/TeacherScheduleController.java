package com.example.yoga_app.controllers;

import com.example.yoga_app.dto.UserProfileDto;
import com.example.yoga_app.entity.Booking;
import com.example.yoga_app.entity.Schedule;
import com.example.yoga_app.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher/schedule")
@PreAuthorize("hasRole('TEACHER')")
public class TeacherScheduleController {

    private final BookingRepository bookingRepository;

    @Operation(summary = "Get students for a schedule", description = "Retrieve all students enrolled in a given schedule (requires TEACHER role).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Students retrieved"),
            @ApiResponse(responseCode = "404", description = "Schedule not found")
    })
    @GetMapping("/{id}/students")
    public List<UserProfileDto> getEnrolledStudents(@PathVariable UUID id) {
        Schedule schedule = new Schedule();
        schedule.setId(id);

        List<Booking> bookings = bookingRepository.findAllBySchedule(schedule);

        return bookings.stream()
                .map(b -> new UserProfileDto(
                        b.getUser().getId(),
                        b.getUser().getUsername(),
                        b.getUser().getEmail()))
                .toList();
    }
}
