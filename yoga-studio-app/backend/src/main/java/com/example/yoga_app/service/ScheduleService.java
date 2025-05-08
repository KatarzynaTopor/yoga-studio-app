package com.example.yoga_app.service;

import com.example.yoga_app.dto.ScheduleRequestDto;
import com.example.yoga_app.dto.ScheduleResponseDto;
import com.example.yoga_app.entity.Instructor;
import com.example.yoga_app.entity.Schedule;
import com.example.yoga_app.entity.Booking;
import com.example.yoga_app.entity.User;
import com.example.yoga_app.mapper.ScheduleMapper;
import com.example.yoga_app.repository.InstructorRepository;
import com.example.yoga_app.repository.ScheduleRepository;
import com.example.yoga_app.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.UUID;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final InstructorRepository instructorRepository;
    private final ScheduleMapper scheduleMapper;
    private final BookingRepository bookingRepository;
    private final EmailQueueSender emailQueueSender;


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

        long booked = bookingRepository.countBySchedule(schedule);
        System.out.println("Current bookings for new class: " + booked);
    }


    @Transactional
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleMapper.toDtoList(scheduleRepository.findAll());
    }


    @Transactional
    public void deleteSchedule(UUID id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found"));

        List<Booking> bookings = bookingRepository.findAllBySchedule(schedule);

        for (Booking booking : bookings) {
            User user = booking.getUser();
            emailQueueSender.sendEmailRequest(user.getEmail());
        }

        bookingRepository.deleteAll(bookings);

        scheduleRepository.delete(schedule);
    }

}
