package fr.mns.accountservice.application.exceptions;

public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException() {
        super("Le montant doit être positif.");
    }
}
