package com.example.yoga_app.service;

import com.example.yoga_app.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import java.time.Duration;
import java.time.Instant;

@RequiredArgsConstructor
public class JwtService {

    private final String issuer;
    private final Duration ttl;
    private final JwtEncoder jwtEncoder;

    public String generateToken(final User user) {
        final var claims = JwtClaimsSet.builder()
                .subject(user.getUsername())
                .claim("roles", user.getRoles())
                .issuer(issuer)
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(ttl))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
