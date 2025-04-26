package com.example.yoga_app.service;

import com.example.yoga_app.dto.ScheduleRequestDto;
import com.example.yoga_app.entity.Instructor;
import com.example.yoga_app.repository.BookingRepository;
import com.example.yoga_app.repository.InstructorRepository;
import com.example.yoga_app.repository.ScheduleRepository;
import com.example.yoga_app.mapper.ScheduleMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private ScheduleMapper scheduleMapper;

    @InjectMocks
    private ScheduleService scheduleService;

    @Test
    void shouldThrowExceptionWhenInstructorNotFound() {
        UUID instructorId = UUID.randomUUID();
        ScheduleRequestDto dto = new ScheduleRequestDto(
                "Title", "Desc", instructorId,
                java.time.LocalDateTime.now(), 10, "Warsaw", "206", 60
        );

        when(instructorRepository.findById(instructorId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> scheduleService.createSchedule(dto));
    }
}
