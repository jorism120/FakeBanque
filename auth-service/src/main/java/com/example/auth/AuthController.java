package com.example.auth;

import java.time.Instant;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtEncoder jwtEncoder;
    private final RestTemplate restTemplate;
    private final String clientServiceUrl;

    public AuthController(JwtEncoder jwtEncoder,
                           RestTemplate restTemplate,
                           @Value("${client.service.url}") String clientServiceUrl) {
        this.jwtEncoder = jwtEncoder;
        this.restTemplate = restTemplate;
        this.clientServiceUrl = clientServiceUrl;
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody RegisterRequest request) {
        ClientDto client;
        try {
            client = restTemplate.postForObject(
                    clientServiceUrl + "/api/clients/register",
                    request,
                    ClientDto.class
            );
        } catch (HttpClientErrorException.Conflict ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Un client avec cet email existe déjà");
        } catch (HttpClientErrorException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inscription invalide");
        } catch (RestClientException ex) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Service client indisponible");
        }

        return generateToken(client);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        ClientDto client;
        try {
            client = restTemplate.postForObject(
                    clientServiceUrl + "/api/clients/authenticate",
                    request,
                    ClientDto.class
            );
        } catch (HttpClientErrorException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants invalides");
        } catch (RestClientException ex) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Service client indisponible");
        }

        return generateToken(client);
    }

    private Map<String, String> generateToken(ClientDto client) {
        if (client == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Service client indisponible");
        }

        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("auth-service")
                .subject(client.email())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(3600))
                .claim("clientId", client.id())
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

record LoginRequest(String email, String password) {}
record RegisterRequest(String firstName, String lastName, String email, String password) {}
record ClientDto(String id, String firstName, String lastName, String email) {}
