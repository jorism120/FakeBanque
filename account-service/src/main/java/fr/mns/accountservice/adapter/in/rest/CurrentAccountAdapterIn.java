package fr.mns.accountservice.adapter.in.rest;

import fr.mns.accountservice.domain.model.CurrentAccount;
import fr.mns.accountservice.domain.port.in.CurrentAccountUseCase;
import fr.mns.accountservice.domain.port.out.TransactionRecorderPort;

public class CurrentAccountAdapterIn {
    private final CurrentAccountUseCase service;

    public CurrentAccountAdapterIn(CurrentAccountUseCase service){
        this.service = service;
    }

    public CurrentAccount create(String iban, String clientId, double initialBalance, double overdraft) {
        return service.create(iban, clientId, initialBalance, overdraft);
    }

    public CurrentAccount getAccount(String iban, String clientId) {
        return service.getAccount(iban, clientId);
    }

    public void deposit(String iban, String clientId, double amount) {
        service.deposit(iban, clientId, amount);
    }

    public void withdraw(String iban, String clientId, double amount) {
        service.withdraw(iban, clientId, amount);
    }
}