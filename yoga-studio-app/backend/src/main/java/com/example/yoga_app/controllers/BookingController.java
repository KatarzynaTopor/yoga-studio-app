package com.example.yoga_app.controllers;

import com.example.yoga_app.dto.BookingDto;
import com.example.yoga_app.entity.Schedule;
import com.example.yoga_app.entity.User;
import com.example.yoga_app.repository.ScheduleRepository;
import com.example.yoga_app.repository.UserRepository;
import com.example.yoga_app.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Booking Controller", description = "Manage user bookings")
public class BookingController {

    private final BookingService bookingService;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Operation(summary = "Book a schedule", description = "User books a class schedule (requires USER role).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully booked"),
            @ApiResponse(responseCode = "400", description = "Class full or other error"),
            @ApiResponse(responseCode = "409", description = "Already booked"),
            @ApiResponse(responseCode = "404", description = "Schedule not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    @PostMapping("/schedule/{id}/book")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> bookSchedule(@PathVariable UUID id, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found"));

        bookingService.createBooking(user, schedule);
        return ResponseEntity.ok("Booked successfully.");
    }

    @Operation(summary = "Cancel a booking", description = "User cancels their class booking (requires USER role).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking cancelled successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Booking or Schedule not found"),
    })
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/schedule/{scheduleId}/cancel")
    public ResponseEntity<?> cancelBooking(
            @PathVariable UUID scheduleId,
            Authentication authentication) {

        User authUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found"));

        bookingService.cancelBooking(authUser, schedule);
        return ResponseEntity.ok("Booking canceled successfully.");
    }

    @Operation(summary = "Get bookings for a teacher", description = "Teacher retrieves bookings for their classes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Teacher not found"),
    })
    @GetMapping("/teacher/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public List<BookingDto> getBookingsByTeacher(@PathVariable UUID id) {
        return bookingService.getBookingsByTeacherId(id);
    }
}
