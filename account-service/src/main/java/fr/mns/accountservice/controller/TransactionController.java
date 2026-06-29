package fr.mns.accountservice.controller;

import fr.mns.accountservice.domain.model.TransactionType;
import fr.mns.accountservice.domain.port.in.TransactionUseCase;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class TransactionController {

    private final TransactionUseCase useCase;

    public TransactionController(TransactionUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/{iban}/transactions")
    public List<TransactionDto> getTransactions(@PathVariable String iban, @AuthenticationPrincipal Jwt jwt) {
        return useCase.findByIban(iban)
                .stream()
                .map(t -> new TransactionDto(t.getId(), t.getIban(), t.getType(), t.getAmount(), t.getTimestamp()))
                .toList();
    }
}

record TransactionDto(
        String id,
        String iban,
        TransactionType type,
        double amount,
        LocalDateTime timestamp
) {
}
