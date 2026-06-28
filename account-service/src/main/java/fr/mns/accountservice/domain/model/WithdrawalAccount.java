package fr.mns.accountservice.domain.model;

public class WithdrawalAccount extends Account {
    public WithdrawalAccount(String iban, String clientId, double initialBalance) {
        super(iban, clientId, initialBalance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant du retrait doit être strictement supérieur à zéro.");
        }
        this.balance -= amount;
    }
}
