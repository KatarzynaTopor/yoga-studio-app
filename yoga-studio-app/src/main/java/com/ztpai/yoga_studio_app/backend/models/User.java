package com.ztpai.yoga_studio_app.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // Dodane!
    private String username;

    @Column(nullable = false) // Dodane!
    private String password;

    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleName role;

    public User(String username, String password, String name, String email, RoleName role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
