package com.example.yoga_app.service;

import static org.springframework.http.HttpStatus.GONE;

import com.example.yoga_app.entity.User;
import com.example.yoga_app.dto.UpdateUserRequestDto;
import com.example.yoga_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByUsername(final String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(GONE, "The user account has been deleted or inactivated"));
    }
    @Transactional
    public User updateUser(String username, UpdateUserRequestDto updateUserRequestDto) {
        var user = getUserByUsername(username);
        user.setUsername(updateUserRequestDto.username());
        user.setEmail(updateUserRequestDto.email());
        return userRepository.save(user);
    }

    // ✅ Delete User
    @Transactional
    public void deleteUser(String username) {
        var user = getUserByUsername(username);
        userRepository.delete(user);
    }
}