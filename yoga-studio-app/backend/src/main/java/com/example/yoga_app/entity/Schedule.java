package com.example.yoga_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "schedule")
@Getter @Setter @NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "yoga_class_id", nullable = false)
    private YogaClass yogaClass;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    @Column(nullable = false)
    private LocalDateTime scheduleTime;

    @Column(nullable = false)
    private int capacity;
}
