package fr.mns.accountservice.domain.model;

import lombok.Data;

@Data
public abstract class Account {
    public String iban;
    public String clientId;
    public double balance;

    public Account(String iban, String clientId, double initialBalance) {
        this.iban = iban;
        this.clientId = clientId;
        this.balance = initialBalance;
    }

    public double checkBalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }
}
