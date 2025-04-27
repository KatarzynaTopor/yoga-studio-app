package com.example.yoga_app.controllers;

import com.example.yoga_app.dto.UserBookingDto;
import com.example.yoga_app.entity.User;
import com.example.yoga_app.repository.UserRepository;
import com.example.yoga_app.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final BookingService bookingService;

    @Operation(summary = "Get user bookings", description = "Retrieve bookings made by a specific user (requires authentication).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings retrieved"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<List<UserBookingDto>> getUserBookings(@PathVariable UUID id, Authentication authentication) {
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getId().equals(id)) {
            return ResponseEntity.status(403).build(); // forbidden
        }

        List<UserBookingDto> response = bookingService.getBookingsForUser(user);
        return ResponseEntity.ok(response);
    }
}
