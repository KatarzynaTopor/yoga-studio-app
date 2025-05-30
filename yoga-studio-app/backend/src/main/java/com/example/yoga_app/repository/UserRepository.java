package com.example.yoga_app.repository;

import com.example.yoga_app.entity.User;
import java.util.Optional;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    List<User> findByRolesContaining(String role);

}