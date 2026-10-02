package fr.mns.accountservice.domain.model;

public abstract class WithdrawalAccount extends Account {
    protected WithdrawalAccount() {
        super();
    }

    public WithdrawalAccount(String iban, String clientId, double balance) {
        super(iban, clientId, balance);
    }

    public abstract void withdraw(double amount);
}
