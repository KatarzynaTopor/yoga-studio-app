package com.example.yoga_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @Column(nullable = false)
    private String status = "CONFIRMED"; // Można też ustawić jako ENUM

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}
