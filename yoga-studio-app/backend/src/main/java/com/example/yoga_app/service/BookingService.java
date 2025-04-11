package com.example.yoga_app.service;

import com.example.yoga_app.dto.UserBookingDto;
import com.example.yoga_app.entity.Booking;
import com.example.yoga_app.entity.Schedule;
import com.example.yoga_app.entity.User;
import com.example.yoga_app.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.yoga_app.dto.BookingDto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    @Transactional(readOnly = true)
    public List<UserBookingDto> getBookingsForUser(User user) {
        return bookingRepository.findAllByUser(user).stream()
                .map(b -> {
                    Schedule s = b.getSchedule();
                    return new UserBookingDto(
                            s.getId(),
                            s.getTitle(),
                            s.getScheduleTime(),
                            s.getInstructor().getName(),
                            s.getLocation(),
                            s.getRoom(),
                            s.getDuration()
                    );
                }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookingDto> getBookingsByTeacherId(UUID teacherId) {
        // Filtrujemy rezerwacje na podstawie nauczyciela
        List<Booking> bookings = bookingRepository.findAll().stream()
                .filter(booking -> booking.getSchedule().getInstructor().getId().equals(teacherId))
                .collect(Collectors.toList());

        return bookings.stream()
                .map(booking -> new BookingDto(
                        booking.getUser().getId(),
                        booking.getSchedule().getId(),
                        booking.getStatus() // Możesz dodać więcej informacji w DTO
                ))
                .collect(Collectors.toList());
    }
}
