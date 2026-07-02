package fr.mns.accountservice.domain.model;

import fr.mns.accountservice.application.exception.ForbiddenException;
import fr.mns.accountservice.application.exception.InvalidAmountException;

public class SavingsAccount extends WithdrawalAccount {
    private double payRate;

    public SavingsAccount(String iban, String clientId, double initialBalance, double payRate) {
        super(iban, clientId, initialBalance);
        this.payRate = payRate;
    }

    @Override
    public void withdraw(double amount) {

        if (amount <= 0)
            throw new InvalidAmountException("Le montant du retrait doit être strictement supérieur à zéro.");

        if (balance - amount < 0)
            throw new ForbiddenException("Vous ne pouvez pas retirer plus que ce qui est disponible sur votre compte épargne.");

        balance -= amount;
    }
}
