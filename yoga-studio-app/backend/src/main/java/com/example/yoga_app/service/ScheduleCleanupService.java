package com.example.yoga_app.service;

import com.example.yoga_app.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleCleanupService {

    private final ScheduleRepository scheduleRepository;

    // Runs every day at 3:00 AM
    @Scheduled(cron = "0 0 3 * * ?")
    public void removePastSchedules() {
        LocalDateTime now = LocalDateTime.now();
        int deletedCount = scheduleRepository.deleteByScheduleTimeBefore(now);
        log.info("Deleted {} past schedule(s)", deletedCount);
    }
}
