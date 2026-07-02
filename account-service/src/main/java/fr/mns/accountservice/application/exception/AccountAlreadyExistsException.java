package fr.mns.accountservice.application.exception;

public class AccountAlreadyExistsException extends RuntimeException {
    public AccountAlreadyExistsException() {
        super("Un compte avec cet IBAN existe déjà");
    }
}
