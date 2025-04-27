package com.example.yoga_app.controllers;

import com.example.yoga_app.dto.AuthenticationResponseDto;
import com.example.yoga_app.service.GoogleAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Google Authentication", description = "Endpoints for Google OAuth2 login")
public class GoogleAuthController {

    private final GoogleAuthService googleAuthService;

    @PostMapping("/oauth2/google")
    public ResponseEntity<AuthenticationResponseDto> authenticateWithGoogle(@RequestBody String idToken) {
        return ResponseEntity.ok(googleAuthService.authenticate(idToken));
    }
}
