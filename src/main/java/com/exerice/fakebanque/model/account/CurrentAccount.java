package com.exerice.fakebanque.model.account;

public class CurrentAccount extends WithdrawalAccount {
    public CurrentAccount(String iban, double initialBalance) {
        super(iban, initialBalance);
    }
}
