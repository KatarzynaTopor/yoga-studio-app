package com.example.yoga_app.controllers;

import com.example.yoga_app.dto.UserProfileDto;
import com.example.yoga_app.dto.UpdateUserRequestDto;
import com.example.yoga_app.mapper.UserMapper;
import com.example.yoga_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/user")
    public ResponseEntity<UserProfileDto> getUserProfile(Authentication authentication) {
        var user = userService.getUserByUsername(authentication.getName());
        return ResponseEntity.ok(userMapper.toUserProfileDto(user));
    }

    @PutMapping("/update_user")
    public ResponseEntity<UserProfileDto> updateUserProfile(
            Authentication authentication,
            @Valid @RequestBody UpdateUserRequestDto updateUserRequestDto) {

        var updatedUser = userService.updateUser(authentication.getName(), updateUserRequestDto);
        return ResponseEntity.ok(userMapper.toUserProfileDto(updatedUser));
    }

    @DeleteMapping("/delete_user")
    public ResponseEntity<Void> deleteUserAccount(Authentication authentication) {
        userService.deleteUser(authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
