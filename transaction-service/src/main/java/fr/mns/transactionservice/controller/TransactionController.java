package fr.mns.transactionservice.controller;

import fr.mns.transactionservice.domain.model.Transaction;
import fr.mns.transactionservice.domain.model.TransactionType;
import fr.mns.transactionservice.domain.port.in.TransactionUseCase;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TransactionController {

    private final TransactionUseCase useCase;

    public TransactionController(TransactionUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/api/transactions")
    public TransactionDto record(@RequestBody RecordTransactionRequest request) {
        Transaction transaction = useCase.record(request.iban(), request.type(), request.amount());
        return toDto(transaction);
    }

    @GetMapping("/api/accounts/{iban}/transactions")
    public List<TransactionDto> getTransactions(@PathVariable String iban, @AuthenticationPrincipal Jwt jwt) {
        return useCase.findByIban(iban)
                .stream()
                .map(this::toDto)
                .toList();
    }

    private TransactionDto toDto(Transaction t) {
        return new TransactionDto(t.getId(), t.getIban(), t.getType(), t.getAmount(), t.getTimestamp());
    }
}

record RecordTransactionRequest(
        String iban,
        TransactionType type,
        double amount
) {
}

record TransactionDto(
        String id,
        String iban,
        TransactionType type,
        double amount,
        LocalDateTime timestamp
) {
}
