package com.example.yoga_app.service;

import com.example.yoga_app.dto.AuthenticationResponseDto;
import com.example.yoga_app.entity.User;
import com.example.yoga_app.repository.UserRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    private static final String CLIENT_ID = "383688280967-jq3l3umrhfnvl4s83654jrc1vit0jacf.apps.googleusercontent.com";

    public AuthenticationResponseDto authenticate(String idTokenString) {
        try {
            var verifier = new GoogleIdTokenVerifier.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    GsonFactory.getDefaultInstance()
            )
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);

            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();

                String email = payload.getEmail();
                String name = (String) payload.get("name");

                // Znajdź lub stwórz użytkownika
                User user = userRepository.findByUsername(email)
                        .orElseGet(() -> {
                            User newUser = new User();
                            newUser.setUsername(email);
                            newUser.setEmail(email);
                            newUser.setPassword("");
                            newUser.setRoles(Collections.singleton("USER"));
                            return userRepository.save(newUser);
                        });

                String token = jwtService.generateToken(user);

                return new AuthenticationResponseDto(
                        token,
                        user.getId(),
                        user.getUsername(),
                        user.getRoles(),
                        user.getInstructor() != null ? user.getInstructor().getId() : null
                );
            } else {
                throw new RuntimeException("Invalid Google ID token");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to authenticate with Google", e);
        }
    }
}
