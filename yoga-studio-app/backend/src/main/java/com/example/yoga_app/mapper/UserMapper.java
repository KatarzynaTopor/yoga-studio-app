package com.example.yoga_app.mapper;

import com.example.yoga_app.dto.UserProfileDto;
import com.example.yoga_app.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserProfileDto toUserProfileDto(User user) {
        return new UserProfileDto(user.getId(), user.getUsername(), user.getEmail());
    }
}
