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

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookingController {

    private final BookingRepository bookingRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @PostMapping("/schedule/{id}/book")
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
}
