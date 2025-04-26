package com.example.yoga_app.service;

import com.example.yoga_app.entity.User;
import com.example.yoga_app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.ArgumentMatchers.anyString;


import java.util.Set;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserRegistrationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserRegistrationService userRegistrationService;

    @Test
    void shouldRegisterUserSuccessfully() {
        User user = new User();
        user.setUsername("user");
        user.setEmail("email@example.com");
        user.setPassword("password");

        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encoded");

        var savedUser = userRegistrationService.registerUser(user);

        assertNotNull(savedUser);
        assertEquals(Set.of("USER"), savedUser.getRoles());
    }
}
