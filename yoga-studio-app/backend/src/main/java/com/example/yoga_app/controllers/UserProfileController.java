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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Get user profile", description = "Retrieve the authenticated user's profile information.")
    @GetMapping("/user")
    public ResponseEntity<UserProfileDto> getUserProfile(Authentication authentication) {
        var user = userService.getUserByUsername(authentication.getName());
        return ResponseEntity.ok(userMapper.toUserProfileDto(user));
    }

    @Operation(summary = "Update user profile", description = "Update the authenticated user's profile information.")
    @PutMapping("/update_user")
    public ResponseEntity<UserProfileDto> updateUserProfile(
            Authentication authentication,
            @Valid @RequestBody UpdateUserRequestDto updateUserRequestDto) {

        var updatedUser = userService.updateUser(authentication.getName(), updateUserRequestDto);
        return ResponseEntity.ok(userMapper.toUserProfileDto(updatedUser));
    }

    @Operation(summary = "Delete user account", description = "Delete the authenticated user's account.")
    @DeleteMapping("/delete_user")
    public ResponseEntity<Void> deleteUserAccount(Authentication authentication) {
        userService.deleteUser(authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
