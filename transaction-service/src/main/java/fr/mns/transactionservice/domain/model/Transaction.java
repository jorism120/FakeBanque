package fr.mns.transactionservice.domain.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Transaction {
    private String id;
    private String iban;
    private TransactionType type;
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(String id, String iban, TransactionType type, double amount, LocalDateTime timestamp) {
        this.id = id;
        this.iban = iban;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }
}
