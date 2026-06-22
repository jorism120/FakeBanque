package fr.mns.accountservice.domain.model;

public class SavingsAccount extends WithdrawalAccount {
    private double payRate;

    public SavingsAccount(String iban, String clientId, double initialBalance, double payRate) {
        super(iban, clientId, initialBalance);
        this.payRate = payRate;
    }
}
