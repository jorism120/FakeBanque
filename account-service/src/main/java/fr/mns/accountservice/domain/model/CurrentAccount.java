package fr.mns.accountservice.domain.model;

import fr.mns.accountservice.application.exception.InvalidAmountException;
import fr.mns.accountservice.application.exception.OverdraftExceededException;

public class CurrentAccount extends WithdrawalAccount {
    private double overdraft;

    public CurrentAccount(String iban, String clientId, double balance, double overdraft) {
        super(iban, clientId, balance);

        if (overdraft > 0) {
            throw new InvalidAmountException("Le découvert doit être inférieur ou égal à zéro.");
        }

        this.overdraft = overdraft;
    }

    protected CurrentAccount() {
        super();
    }

    public static CurrentAccount rehydrate(
            String iban,
            String clientId,
            double balance,
            double overdraft) {

        CurrentAccount account = new CurrentAccount();
        account.iban = iban;
        account.clientId = clientId;
        account.balance = balance;
        account.overdraft = overdraft;

        return account;
    }

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }

    @Override
    public void withdraw(double amount) {

        if (amount <= 0)
            throw new InvalidAmountException("Le montant du retrait doit être strictement supérieur à zéro.");

        if (balance - amount < overdraft)
            throw new OverdraftExceededException();

        balance -= amount;
    }
}
