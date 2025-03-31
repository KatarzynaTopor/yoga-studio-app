package com.example.yoga_app.mapper;

import com.example.yoga_app.dto.ScheduleResponseDto;
import com.example.yoga_app.entity.Schedule;
import com.example.yoga_app.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScheduleMapper {

    private final BookingRepository bookingRepository;

    public ScheduleResponseDto toDto(Schedule schedule) {
        int booked = (int) bookingRepository.countBySchedule(schedule);

        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getScheduleTime(),
                schedule.getCapacity(),
                schedule.getInstructor().getName(),
                schedule.getLocation(),
                schedule.getRoom(),
                schedule.getDuration(),
                booked
        );
    }

    public List<ScheduleResponseDto> toDtoList(List<Schedule> schedules) {
        return schedules.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
