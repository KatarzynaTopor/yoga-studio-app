package com.example.yoga_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "instructors")
@Getter @Setter @NoArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String bio;

    private String experience;

    private String specialties;

    private String imageUrl;
}
