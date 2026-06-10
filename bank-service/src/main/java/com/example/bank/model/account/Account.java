package com.example.bank.model.account;

import lombok.Data;

@Data
public abstract class Account {
    public String iban;
    public double balance;

    public Account(String iban, double initialBalance) {
        this.iban = iban;
        this.balance = initialBalance;
    }

    public double checkBalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }
}
