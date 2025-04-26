package com.example.yoga_app.service;

import com.example.yoga_app.dto.AuthenticationRequestDto;
import com.example.yoga_app.dto.AuthenticationResponseDto;
import com.example.yoga_app.entity.User;
import com.example.yoga_app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void shouldAuthenticateUserSuccessfully() {
        AuthenticationRequestDto request = new AuthenticationRequestDto("testuser", "password");
        User user = new User();
        user.setUsername("testuser");
        user.setRoles(Set.of("USER"));
        user.setId(UUID.randomUUID());

        when(userRepository.findByUsername("testuser")).thenReturn(java.util.Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("fake-jwt-token");

        AuthenticationResponseDto response = authenticationService.authenticate(request);

        assertEquals("fake-jwt-token", response.token());
        assertEquals(user.getUsername(), response.username());
    }
}
