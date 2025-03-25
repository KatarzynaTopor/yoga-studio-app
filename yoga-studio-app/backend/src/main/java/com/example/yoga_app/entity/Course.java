package com.example.yoga_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "courses")
@Getter @Setter @NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String duration; // e.g. 6 weeks

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "yoga_class_id")
    private YogaClass yogaClass;
}
