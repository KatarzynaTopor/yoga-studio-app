package com.example.yoga_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "levels")
@Getter @Setter @NoArgsConstructor
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name; // e.g. Beginner, Intermediate

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private int stars; // number of stars (1-5)
}
