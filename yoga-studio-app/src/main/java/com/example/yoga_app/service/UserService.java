package com.example.yoga_app.service;

import static org.springframework.http.HttpStatus.GONE;

import com.example.yoga_app.entity.User;
import com.example.yoga_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByUsername(final String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(GONE, "The user account has been deleted or inactivated"));
    }
}