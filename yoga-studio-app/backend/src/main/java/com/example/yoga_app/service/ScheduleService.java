package com.example.yoga_app.service;

import com.example.yoga_app.dto.ScheduleRequestDto;
import com.example.yoga_app.dto.ScheduleResponseDto;
import com.example.yoga_app.entity.Instructor;
import com.example.yoga_app.entity.Schedule;
import com.example.yoga_app.mapper.ScheduleMapper;
import com.example.yoga_app.repository.InstructorRepository;
import com.example.yoga_app.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // <- TO DODAĆ
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final InstructorRepository instructorRepository;
    private final ScheduleMapper scheduleMapper;

    public void createSchedule(ScheduleRequestDto dto) {
        Instructor instructor = instructorRepository.findById(dto.instructorId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Instructor not found: " + dto.instructorId()
                ));

        Schedule schedule = new Schedule();
        schedule.setTitle(dto.title());
        schedule.setDescription(dto.description());
        schedule.setInstructor(instructor);
        schedule.setScheduleTime(dto.scheduleTime());
        schedule.setCapacity(dto.capacity());
        schedule.setLocation(dto.location());
        schedule.setRoom(dto.room());
        schedule.setDuration(dto.duration());

        scheduleRepository.save(schedule);
    }

    @Transactional
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleMapper.toDtoList(scheduleRepository.findAll());
    }
}
