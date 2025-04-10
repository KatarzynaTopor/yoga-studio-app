package com.example.yoga_app.service;

import com.example.yoga_app.dto.AuthenticationRequestDto;
import com.example.yoga_app.dto.AuthenticationResponseDto;
import com.example.yoga_app.entity.User;
import com.example.yoga_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ Użyj username, jeśli metoda wymaga Stringa
        String token = jwtService.generateToken(user.getUsername());

        return new AuthenticationResponseDto(token, user.getId(), user.getUsername());
    }
}
