package com.example.yoga_app.mapper;

import com.example.yoga_app.dto.ScheduleResponseDto;
import com.example.yoga_app.entity.Schedule;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleMapper {

    public ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getScheduleTime(),
                schedule.getCapacity(),
                schedule.getInstructor().getName(),
                schedule.getLocation(),
                schedule.getRoom(),
                schedule.getDuration()
        );
    }

    public List<ScheduleResponseDto> toDtoList(List<Schedule> schedules) {
        return schedules.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}