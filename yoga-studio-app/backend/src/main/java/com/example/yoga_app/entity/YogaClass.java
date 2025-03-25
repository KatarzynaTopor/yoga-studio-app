package com.example.yoga_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "yoga_classes")
@Getter @Setter @NoArgsConstructor
public class YogaClass {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name; // e.g. Hatha Yoga

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;
}
