package com.example.auth;

import java.time.Instant;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtEncoder jwtEncoder;

    public AuthController(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        System.out.println("USERNAME = " + request.username());
        System.out.println("PASSWORD = " + request.password());
        if (!request.username().equals("stephane") ||
            !request.password().equals("joyeux")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("auth-service")
                .subject("stephane")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(3600))
                .claim("clientId", "C001")
                .claim("scope", "accounts:read")
                .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();

        String token = jwtEncoder.encode(
                JwtEncoderParameters.from(header, claims)
        ).getTokenValue();

        return Map.of(
                "access_token", token,
                "token_type", "Bearer"
        );
    }
}

record LoginRequest(String username, String password) {}