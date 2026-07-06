package fr.mns.accountservice.controller;

import fr.mns.accountservice.domain.model.CurrentAccount;
import fr.mns.accountservice.domain.port.in.CurrentAccountUseCase;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class CurrentAccountController {

    private final CurrentAccountUseCase useCase;

    public CurrentAccountController(CurrentAccountUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public List<CurrentAccount> getAccounts(@AuthenticationPrincipal Jwt jwt) {
        String clientId = jwt.getClaimAsString("clientId");

        return useCase.getAccounts(clientId);
    }

    @GetMapping("/{iban}")
    public CurrentAccountDto getAccount(@PathVariable String iban, @AuthenticationPrincipal Jwt jwt) {
        CurrentAccount account = useCase.getAccount(iban, jwt.getClaimAsString("clientId"));

        return new CurrentAccountDto(
                account.getIban(),
                account.checkBalance(),
                account.getOverdraft()
        );
    }

    @PostMapping("/create")
    public CurrentAccountDto createAccount(@RequestBody CurrentAccountDto request, @AuthenticationPrincipal Jwt jwt) {
        CurrentAccount account = useCase.create(
                request.iban(),
                jwt.getClaimAsString("clientId"),
                request.balance(),
                request.overdraft());

        return new CurrentAccountDto(
                account.getIban(),
                account.checkBalance(),
                account.getOverdraft());
    }

    @PostMapping("/{iban}/deposit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deposit(
            @PathVariable String iban,
            @RequestBody DepositRequest request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        useCase.deposit(
                iban,
                jwt.getClaimAsString("clientId"),
                request.amount());
    }

    @PostMapping("/{iban}/withdraw")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdraw(
            @PathVariable String iban,
            @RequestBody WithdrawRequest request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        useCase.withdraw(
                iban,
                jwt.getClaimAsString("clientId"),
                request.amount());
    }
}

record CurrentAccountDto(
        @NotBlank
        String iban,

        @PositiveOrZero
        double balance,

        @NegativeOrZero
        double overdraft
) {
}
record DepositRequest(
        @Positive(message = "Le montant doit être supérieur à zéro")
        double amount
) {
}
record WithdrawRequest(
        @Positive(message = "Le montant doit être supérieur à zéro")
        double amount
) {
}