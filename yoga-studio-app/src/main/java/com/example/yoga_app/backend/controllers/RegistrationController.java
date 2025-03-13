package com.example.yoga_app.backend.controllers;

import com.example.yoga_app.backend.dto.RegistrationRequestDto;
import com.example.yoga_app.backend.dto.RegistrationResponseDto;
import com.example.yoga_app.backend.mapper.UserRegistrationMapper;
import com.example.yoga_app.backend.service.UserRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserRegistrationService userRegistrationService;

    private final UserRegistrationMapper userRegistrationMapper;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDto> registerUser(@Valid @RequestBody final RegistrationRequestDto registrationDTO) {
        final var registeredUser = userRegistrationService.registerUser(userRegistrationMapper.toEntity(registrationDTO));

        return ResponseEntity.ok(userRegistrationMapper.toRegistrationResponseDto(registeredUser));
    }

}