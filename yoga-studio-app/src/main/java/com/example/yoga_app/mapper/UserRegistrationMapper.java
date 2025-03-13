package com.example.yoga_app.mapper;

import com.example.yoga_app.dto.RegistrationRequestDto;
import com.example.yoga_app.dto.RegistrationResponseDto;
import com.example.yoga_app.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationMapper {

    public User toEntity(RegistrationRequestDto registrationRequestDto) {
        final var user = new User();

        user.setEmail(registrationRequestDto.email());
        user.setUsername(registrationRequestDto.username());
        user.setPassword(registrationRequestDto.password());

        return user;
    }

    public RegistrationResponseDto toRegistrationResponseDto(final User user) {
        return new RegistrationResponseDto(user.getEmail(), user.getUsername());
    }

}