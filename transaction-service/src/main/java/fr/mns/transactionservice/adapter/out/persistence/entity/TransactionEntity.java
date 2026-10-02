package fr.mns.transactionservice.adapter.out.persistence.entity;

import fr.mns.transactionservice.domain.model.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
public class TransactionEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String iban;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    private double amount;

    private LocalDateTime timestamp;
}
