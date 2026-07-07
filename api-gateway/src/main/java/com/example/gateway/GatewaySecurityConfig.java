package com.example.gateway;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


import java.util.List;


@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfig {

    @Bean
    SecurityWebFilterChain security(ServerHttpSecurity http) {
        return http
                // 1. On active explicitement CORS et Spring WebFlux va automatiquement chercher le Bean réactif ci-dessous
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(auth -> auth
                        .pathMatchers("/auth/**").permitAll()
                        .pathMatchers("/api/accounts/**").hasAuthority("SCOPE_accounts:read")
                        .pathMatchers("/api/clients/**").hasAuthority("SCOPE_accounts:read")
                        .pathMatchers("/api/accounts/*/transactions").hasAuthority("SCOPE_accounts:read")
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {}))
                .build();
    }

    @Bean
    ReactiveJwtDecoder jwtDecoder(@Value("${jwt.secret}") String secret) {
        return NimbusReactiveJwtDecoder
                .withSecretKey(new SecretKeySpec(secret.getBytes(), "HmacSHA256"))
                .build();
    }

    // 2. Utilisation des classes réactives CorsConfigurationSource et UrlBasedCorsConfigurationSource
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With"));
        configuration.setAllowCredentials(true);

        // Attention à bien importer org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}