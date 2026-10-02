package fr.mns.accountservice.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name = "account")
@Data
public class AccountEntity {

    @Id
    private String iban;

    @Column(nullable = false)
    private String clientId;

    private double balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType type;

    @Column(nullable = false)
    private Double overdraft;

    private Boolean taxed;
}


