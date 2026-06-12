package com.example.bank.adapter.in.rest;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @GetMapping
    public List<AccountDto> accounts(@AuthenticationPrincipal Jwt jwt) {

        String clientId = jwt.getClaimAsString("clientId");

        if (!"C001".equals(clientId)) {
            return List.of();
        }

        return List.of(
                new AccountDto("FR7612340001", "Compte courant", 2500.75, "EUR"),
                new AccountDto("FR7612340002", "Livret A", 10800.00, "EUR")
        );
    }
}

record AccountDto(
        String iban,
        String label,
        double balance,
        String currency
) {}