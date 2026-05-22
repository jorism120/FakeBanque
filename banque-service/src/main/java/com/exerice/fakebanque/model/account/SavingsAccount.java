package com.exerice.fakebanque.model.account;

public class SavingsAccount extends WithdrawalAccount {
    private double payRate;

    public SavingsAccount(String iban, double initialBalance, double payRate) {
        super(iban, initialBalance);
        this.payRate = payRate;
    }
}
