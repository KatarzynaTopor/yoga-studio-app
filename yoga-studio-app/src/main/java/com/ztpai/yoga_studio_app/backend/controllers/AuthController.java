package com.ztpai.yoga_studio_app.backend.controllers;

import com.ztpai.yoga_studio_app.backend.models.RoleName;
import com.ztpai.yoga_studio_app.backend.models.User;
import com.ztpai.yoga_studio_app.backend.repositories.UserRepository;
import com.ztpai.yoga_studio_app.backend.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // ✅ User Registration
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String name = request.get("name");
        String email = request.get("email");

        // Check if user already exists
        if (userRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username already exists"));
        }

        // Encode password before saving
        String encodedPassword = passwordEncoder.encode(password);

        // Create new user
        User newUser = new User(username, encodedPassword, name, email, RoleName.USER);
        userRepository.save(newUser);

        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }

    // ✅ User Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // Generate JWT Token
        String token = jwtTokenProvider.generateToken(authentication);
        Optional<User> user = userRepository.findByUsername(username);

        return user.map(value -> ResponseEntity.ok(Map.of(
                "token", token,
                "username", value.getUsername(),
                "role", value.getRole().name()
        ))).orElseGet(() -> ResponseEntity.status(403).body(Map.of("error", "User not found")));
    }
}
