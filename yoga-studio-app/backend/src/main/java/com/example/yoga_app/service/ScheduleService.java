package com.example.yoga_app.service;

import com.example.yoga_app.dto.ScheduleRequestDto;
import com.example.yoga_app.entity.Instructor;
import com.example.yoga_app.entity.Schedule;
import com.example.yoga_app.repository.InstructorRepository;
import com.example.yoga_app.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final InstructorRepository instructorRepository;

    public void createSchedule(ScheduleRequestDto dto) {
        Instructor instructor = instructorRepository.findByName(dto.instructor())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Instructor not found: " + dto.instructor()
                ));

        Schedule schedule = new Schedule();
        schedule.setTitle(dto.title());
        schedule.setDescription(dto.description());
        schedule.setInstructor(instructor);
        schedule.setScheduleTime(dto.scheduleTime());
        schedule.setCapacity(dto.capacity());

        scheduleRepository.save(schedule);
    }
}
