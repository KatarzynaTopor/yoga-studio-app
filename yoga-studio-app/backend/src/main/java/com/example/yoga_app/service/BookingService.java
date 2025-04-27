package com.example.yoga_app.service;

import com.example.yoga_app.dto.UserBookingDto;
import com.example.yoga_app.dto.BookingDto;
import com.example.yoga_app.entity.Booking;
import com.example.yoga_app.entity.Schedule;
import com.example.yoga_app.entity.User;
import com.example.yoga_app.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final EmailService emailService;

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
        List<Booking> bookings = bookingRepository.findAll().stream()
                .filter(booking -> booking.getSchedule().getInstructor().getId().equals(teacherId))
                .collect(Collectors.toList());

        return bookings.stream()
                .map(booking -> new BookingDto(
                        booking.getUser().getId(),
                        booking.getSchedule().getId(),
                        booking.getStatus()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public Booking createBooking(User user, Schedule schedule) {
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setSchedule(schedule);
        booking.setStatus("CONFIRMED");

        Booking savedBooking = bookingRepository.save(booking);

        // Wysyłamy e-mail potwierdzający zapis
        emailService.sendEmail(
                user.getEmail(),
                "Potwierdzenie zapisu na zajęcia",
                "Cześć " + user.getUsername() + "!<br>Zapisałeś/aś się na zajęcia: <strong>" + schedule.getTitle() + "</strong>."
        );

        return savedBooking;
    }

    @Transactional
    public void cancelBooking(User user, Schedule schedule) {
        Booking booking = bookingRepository.findByUserAndSchedule(user, schedule)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found"));

        bookingRepository.delete(booking);

        emailService.sendEmail(
                user.getEmail(),
                "Anulowanie zapisu na zajęcia",
                "Cześć " + user.getUsername() + "!<br>Wypisałeś/aś się z zajęć: <strong>" + schedule.getTitle() + "</strong>."
        );
    }


    @Transactional
    public void cancelScheduleByTeacher(Schedule schedule) {
        List<Booking> bookings = bookingRepository.findAll()
                .stream()
                .filter(b -> b.getSchedule().equals(schedule))
                .collect(Collectors.toList());

        for (Booking booking : bookings) {
            User user = booking.getUser();
            // Wysyłamy maila do każdego uczestnika
            emailService.sendEmail(
                    user.getEmail(),
                    "Odwołanie zajęć",
                    "Cześć " + user.getUsername() + "!<br>Zajęcia <strong>" + schedule.getTitle() + "</strong> zostały odwołane przez nauczyciela."
            );
            bookingRepository.delete(booking);
        }
    }
}
