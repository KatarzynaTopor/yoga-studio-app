package com.example.yoga_app.service;

import static org.springframework.http.HttpStatus.CONFLICT;

import com.example.yoga_app.entity.User;
import com.example.yoga_app.exception.ValidationException;
import com.example.yoga_app.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User registerUser(User user) {
        final var errors = new HashMap<String, String>();

        if (userRepository.existsByEmail(user.getEmail())) {
            errors.put("email", "Email [%s] is already taken".formatted(user.getEmail()));
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            errors.put("username", "Username [%s] is already taken".formatted(user.getUsername()));
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(CONFLICT, errors);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

}