package com.example.yoga_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "instructor_specialties", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"instructor_id", "specialty_id"})
})
@Getter @Setter @NoArgsConstructor
public class InstructorSpecialty {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "specialty_id", nullable = false)
    private Specialty specialty;
}
