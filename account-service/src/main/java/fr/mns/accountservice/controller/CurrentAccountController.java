package fr.mns.accountservice.controller;

import fr.mns.accountservice.domain.model.CurrentAccount;
import fr.mns.accountservice.domain.port.in.CurrentAccountUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class CurrentAccountController {

    private final CurrentAccountUseCase useCase;

    public CurrentAccountController(CurrentAccountUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/{iban}")
    public CurrentAccountDto getAccount(@PathVariable String iban, @AuthenticationPrincipal Jwt jwt) {

        String clientId = jwt.getClaimAsString("clientId");

        if (!"C001".equals(clientId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        CurrentAccount account =
                useCase.findByIban(iban);

        return new CurrentAccountDto(
                account.getIban(),
                account.checkBalance(),
                account.getOverdraft()
        );
    }

    @PostMapping
    public CurrentAccountDto createAccount(@AuthenticationPrincipal Jwt jwt) {

        String clientId = jwt.getClaimAsString("clientId");

        CurrentAccount account = useCase.create("FR7612340001", clientId, 1000, 500);

        return new CurrentAccountDto(
                account.getIban(),
                account.checkBalance(),
                account.getOverdraft()
        );
    }

    @PostMapping("/{iban}/deposit")
    public void deposit(
            @PathVariable String iban,
            @RequestBody DepositRequest request,
            @AuthenticationPrincipal Jwt jwt) {

        String clientId = jwt.getClaimAsString("clientId");

        if ("C001".equals(clientId)) {
            useCase.deposit(
                    iban,
                    request.amount()
            );
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/{iban}/withdraw")
    public void withdraw(
            @PathVariable String iban,
            @RequestBody WithdrawRequest request,
            @AuthenticationPrincipal Jwt jwt) {

        String clientId = jwt.getClaimAsString("clientId");

        if ("C001".equals(clientId)) {
            useCase.withdraw(
                    iban,
                    request.amount()
            );
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}

record CurrentAccountDto(
        String iban,
        double balance,
        double overdraft
) {
}
record DepositRequest(
        double amount
) {
}
record WithdrawRequest(
        double amount
) {
}