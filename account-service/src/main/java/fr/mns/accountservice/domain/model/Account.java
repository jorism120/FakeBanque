package fr.mns.accountservice.domain.model;

import fr.mns.accountservice.application.exception.InvalidAmountException;
import lombok.Data;

@Data
public abstract class Account {
    public String iban;
    public String clientId;
    public double balance;

    protected Account() {
        // pour la réhydratation
    }

    public Account(String iban, String clientId, double balance) {
        this.iban = iban;
        this.clientId = clientId;

        if (balance < 0.0) {
            throw new InvalidAmountException("Le montant initial pour ouvrir un compte ne peut pas être négatif.");
        }

        this.balance = balance;
    }

    public double checkBalance() {
        return this.balance;
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            throw new InvalidAmountException("Vous devez déposer un montant positif.");
        }

        balance += amount;
    }
}
