package com.example.yoga_app.seed;

import com.example.yoga_app.entity.Instructor;
import com.example.yoga_app.entity.Schedule;
import com.example.yoga_app.repository.InstructorRepository;
import com.example.yoga_app.repository.ScheduleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class ScheduleSeeder {

    private final InstructorRepository instructorRepository;
    private final ScheduleRepository scheduleRepository;

    private static final List<String> TITLES = List.of("Hatha Yoga", "Vinyasa Yoga", "Ashtanga Yoga", "Yin Yoga");
    private static final List<String> LOCATIONS = List.of("Krakow Studio", "Warsaw Studio");
    private static final List<String> ROOMS = List.of("206", "207", "208", "501");
    private static final List<Integer> DURATIONS = List.of(45, 60, 75, 90);
    private static final Map<String, String> TITLE_DESCRIPTIONS = Map.of(
            "Hatha Yoga", "A gentle introduction to basic yoga postures and breathing techniques.",
            "Vinyasa Yoga", "A dynamic flow of poses synchronized with breath to energize your body.",
            "Ashtanga Yoga", "A structured and challenging sequence for building strength and flexibility.",
            "Yin Yoga", "A meditative class with long-held, passive stretches for deep relaxation."
    );

    private final Random random = new Random();

    @PostConstruct
    public void seedSchedules() {
        List<Instructor> instructors = instructorRepository.findAll();
        if (instructors.isEmpty()) return;

        LocalDate today = LocalDate.now();

        for (Instructor instructor : instructors) {
            for (int dayOffset = 0; dayOffset < 14; dayOffset++) {
                LocalDate date = today.plusDays(dayOffset);

                for (int i = 0; i < 2; i++) {
                    Schedule schedule = new Schedule();

                    String title = getRandomElement(TITLES);
                    schedule.setTitle(title);
                    schedule.setDescription(TITLE_DESCRIPTIONS.getOrDefault(title, "Yoga class."));

                    LocalTime time = getRandomTimeSlot(instructor.getId(), dayOffset, i);
                    schedule.setScheduleTime(LocalDateTime.of(date, time));

                    schedule.setInstructor(instructor);
                    schedule.setCapacity(20 + random.nextInt(10));
                    schedule.setLocation(getRandomElement(LOCATIONS));
                    schedule.setRoom(getRandomElement(ROOMS));
                    schedule.setDuration(getRandomElement(DURATIONS));

                    scheduleRepository.save(schedule);
                }
            }
        }
    }

    private <T> T getRandomElement(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }

    private LocalTime getRandomTimeSlot(UUID instructorId, int dayOffset, int slotIndex) {
        int baseHour = 7 + (Math.abs(Objects.hash(instructorId, dayOffset, slotIndex)) % 11); // Hours between 7–17
        int[] minuteOptions = {0, 15, 30, 45};
        int minutes = minuteOptions[random.nextInt(minuteOptions.length)];
        return LocalTime.of(baseHour, minutes);
    }
}
