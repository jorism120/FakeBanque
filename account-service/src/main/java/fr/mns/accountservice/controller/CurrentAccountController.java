package fr.mns.accountservice.controller;

import fr.mns.accountservice.domain.model.CurrentAccount;
import fr.mns.accountservice.domain.port.in.CurrentAccountUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/accounts")
public class CurrentAccountController {

    private final CurrentAccountUseCase useCase;

    public CurrentAccountController(CurrentAccountUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/{iban}")
    public CurrentAccountDto getAccount(@PathVariable String iban, @AuthenticationPrincipal Jwt jwt) {

        String clientId = jwt.getClaimAsString("clientId");

        CurrentAccount account = useCase.findByIban(iban)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!clientId.equals(account.getClientId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return new CurrentAccountDto(
                account.getIban(),
                account.checkBalance(),
                account.getOverdraft()
        );
    }

    @PostMapping("/create")
    public CurrentAccountDto createAccount(
            @RequestBody CurrentAccountDto request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        if (useCase.findByIban(request.iban()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Un compte avec cet IBAN existe déjà");
        }
        String clientId = jwt.getClaimAsString("clientId");
        if (clientId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le client ID doit être renseigné");
        }
        if (request.balance() == 0.0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le solde ne peut pas être égal à zéro");
        }
        if (request.overdraft() > 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le découvert est une valeur négative");
        }

        CurrentAccount account = useCase.create(request.iban(), clientId, request.balance(), request.overdraft());

        return new CurrentAccountDto(account.getIban(), account.checkBalance(), account.getOverdraft());
    }

    @PostMapping("/{iban}/deposit")
    public void deposit(
            @PathVariable String iban,
            @RequestBody DepositRequest request,
            @AuthenticationPrincipal Jwt jwt) {

        String clientId = jwt.getClaimAsString("clientId");

        CurrentAccount account = useCase.findByIban(iban)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!clientId.equals(account.getClientId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        useCase.deposit(iban, request.amount());
    }

    @PostMapping("/{iban}/withdraw")
    public void withdraw(
            @PathVariable String iban,
            @RequestBody WithdrawRequest request,
            @AuthenticationPrincipal Jwt jwt) {

        String clientId = jwt.getClaimAsString("clientId");

        CurrentAccount account = useCase.findByIban(iban)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!clientId.equals(account.getClientId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        useCase.withdraw(iban, request.amount());
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