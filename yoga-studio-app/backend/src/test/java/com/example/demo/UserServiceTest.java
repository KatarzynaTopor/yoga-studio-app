package com.example.yoga_app.service;

import com.example.yoga_app.entity.User;
import com.example.yoga_app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        when(userRepository.findByUsername("user")).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> userService.getUserByUsername("user"));
    }
}
