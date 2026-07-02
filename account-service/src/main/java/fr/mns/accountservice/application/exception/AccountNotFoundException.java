package fr.mns.accountservice.application.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException() {
        super("Le compte n'existe pas.");
    }
}
