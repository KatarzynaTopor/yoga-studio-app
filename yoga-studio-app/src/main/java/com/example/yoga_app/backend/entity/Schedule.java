package com.example.yoga_app.backend.entity;

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

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String instructor;

    @Column(nullable = false)
    private LocalDateTime scheduleTime;

    @Column(nullable = false)
    private int capacity;
}
