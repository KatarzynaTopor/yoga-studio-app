package com.example.yoga_app.repository;

import com.example.yoga_app.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import java.util.UUID;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    @Transactional
    @Modifying
    int deleteByScheduleTimeBefore(LocalDateTime dateTime);
    List<Schedule> findByScheduleTimeAfter(LocalDateTime dateTime);

}
