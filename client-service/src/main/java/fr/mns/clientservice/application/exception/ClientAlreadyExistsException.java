package fr.mns.clientservice.application.exception;

public class ClientAlreadyExistsException extends RuntimeException {
    public ClientAlreadyExistsException() {
        super("Un client avec cet email existe déjà");
    }
}
