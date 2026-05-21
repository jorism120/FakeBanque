package com.exerice.fakebanque.model.account;

public class WithdrawalAccount extends Account {
    public WithdrawalAccount(String iban, double initialBalance) {
        super(iban, initialBalance);
    }

    public void withdraw(double amount) {
        this.balance += amount;
    }
}
