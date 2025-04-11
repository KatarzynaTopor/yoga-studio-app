package com.example.yoga_app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.yoga_app.entity.User;
import com.example.yoga_app.entity.Schedule;
import com.example.yoga_app.entity.Booking;
import com.example.yoga_app.repository.BookingRepository;
import com.example.yoga_app.repository.ScheduleRepository;
import com.example.yoga_app.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import com.example.yoga_app.dto.BookingDto;
import com.example.yoga_app.service.BookingService; // <-- Dodaj ten import

import java.util.UUID;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookingController {

    private final BookingRepository bookingRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final BookingService bookingService; // <-- Dodaj to pole

    @PostMapping("/schedule/{id}/book")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> bookSchedule(@PathVariable UUID id, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found"));

        long currentCount = bookingRepository.countBySchedule(schedule);

        if (currentCount >= schedule.getCapacity()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Class is full.");
        }

        boolean alreadyBooked = bookingRepository.existsByUserAndSchedule(user, schedule);
        if (alreadyBooked) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Already booked.");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setSchedule(schedule);
        bookingRepository.save(booking);

        return ResponseEntity.ok("Booked successfully.");
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/schedule/{scheduleId}/cancel")
    public ResponseEntity<?> cancelBooking(
            @PathVariable UUID scheduleId,
            @RequestParam UUID userId,
            Authentication authentication) {

        User authUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!authUser.getId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized user");
        }

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found"));

        Booking booking = bookingRepository.findByUserAndSchedule(authUser, schedule)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found"));

        bookingRepository.delete(booking);

        return ResponseEntity.ok("Booking canceled successfully.");
    }

    @GetMapping("/teacher/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public List<BookingDto> getBookingsByTeacher(@PathVariable UUID id) {
        return bookingService.getBookingsByTeacherId(id);  // To wywołanie działa teraz poprawnie
    }
}
