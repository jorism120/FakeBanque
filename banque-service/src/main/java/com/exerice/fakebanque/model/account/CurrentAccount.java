package com.exerice.fakebanque.model.account;

public class CurrentAccount extends WithdrawalAccount {
    private double overdraft;

    public CurrentAccount(String iban, double initialBalance, double overdraft) {
        super(iban, initialBalance);
        this.overdraft = overdraft;
    }
}
