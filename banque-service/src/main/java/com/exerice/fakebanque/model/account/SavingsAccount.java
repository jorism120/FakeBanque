package com.exerice.fakebanque.model.account;

public class SavingsAccount extends WithdrawalAccount {
    public SavingsAccount(String iban, double initialBalance) {
        super(iban, initialBalance);
    }
}
