package fr.mns.accountservice.domain.model;

public abstract class WithdrawalAccount extends Account {
    public WithdrawalAccount(String iban, String clientId, double initialBalance) {
        super(iban, clientId, initialBalance);
    }

    public abstract void withdraw(double amount);
}
