package fr.mns.accountservice.application.exception;

public class OverdraftExceededException extends RuntimeException {
    public OverdraftExceededException() {
        super("Le découvert a été dépassé.");
    }
}
