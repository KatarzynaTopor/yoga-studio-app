package com.example.yoga_app.service;

import com.example.yoga_app.entity.Booking;
import com.example.yoga_app.entity.Schedule;
import com.example.yoga_app.entity.User;
import com.example.yoga_app.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void shouldReturnBookingsForUser() {
        User user = new User();
        user.setUsername("testuser");

        Schedule schedule = new Schedule();
        schedule.setTitle("Yoga Class");

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setSchedule(schedule);

        when(bookingRepository.findAllByUser(user)).thenReturn(List.of(booking));

        var bookings = bookingService.getBookingsForUser(user);

        assertEquals(1, bookings.size());
        assertEquals("Yoga Class", bookings.get(0).title());
    }
}
