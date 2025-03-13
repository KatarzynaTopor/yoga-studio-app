package com.example.yoga_app.service;

import com.example.yoga_app.entity.Schedule;
import com.example.yoga_app.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }


}
