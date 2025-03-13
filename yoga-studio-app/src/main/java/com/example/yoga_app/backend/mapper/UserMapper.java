package com.example.yoga_app.backend.mapper;

import com.example.yoga_app.backend.dto.UserProfileDto;
import com.example.yoga_app.backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserProfileDto toUserProfileDto(User user) {
        return new UserProfileDto(user.getEmail(), user.getUsername());
    }

}

