package com.example.yoga_app.controllers;

import com.example.yoga_app.dto.AuthenticationRequestDto;
import com.example.yoga_app.dto.AuthenticationResponseDto;
import com.example.yoga_app.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication (login)")
public class AuthController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Authenticate user", description = "Authenticate a user and return a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful"),
            @ApiResponse(responseCode = "401", description = "Authentication failed"),
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody final AuthenticationRequestDto authenticationRequestDto) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequestDto));
    }
}
