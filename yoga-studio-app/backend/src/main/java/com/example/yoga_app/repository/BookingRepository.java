package com.example.yoga_app.repository;

import com.example.yoga_app.entity.Booking;
import com.example.yoga_app.entity.Schedule;
import com.example.yoga_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    long countBySchedule(Schedule schedule);
    boolean existsByUserAndSchedule(User user, Schedule schedule);

    List<Booking> findAllBySchedule(Schedule schedule);
    List<Booking> findAllByUser(User user);

}
