package fr.mns.accountservice.domain.model;

public class WithdrawalAccount extends Account {
    public WithdrawalAccount(String iban, String clientId, double initialBalance) {
        super(iban, clientId, initialBalance);
    }

    public void withdraw(double amount) {
        this.balance += amount;
    }
}
