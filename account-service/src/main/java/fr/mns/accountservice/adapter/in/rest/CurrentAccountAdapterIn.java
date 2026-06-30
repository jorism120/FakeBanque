package fr.mns.accountservice.adapter.in.rest;

import fr.mns.accountservice.domain.model.CurrentAccount;
import fr.mns.accountservice.domain.port.in.CurrentAccountUseCase;
import fr.mns.accountservice.domain.port.out.CurrentAccountRepository;
import fr.mns.accountservice.domain.port.out.TransactionRecorderPort;

public class CurrentAccountAdapterIn implements CurrentAccountUseCase {
    private final CurrentAccountRepository repository;
    private final TransactionRecorderPort transactionRecorderPort;

    public CurrentAccountAdapterIn(CurrentAccountRepository repository, TransactionRecorderPort transactionRecorderPort) {
        this.repository = repository;
        this.transactionRecorderPort = transactionRecorderPort;
    }

    @Override
    public CurrentAccount create(String iban, String clientId, double initialBalance, double overdraft) {
        CurrentAccount account = new CurrentAccount(iban, clientId, initialBalance, overdraft);
        return repository.save(account);
    }

    @Override
    public CurrentAccount findByIban(String iban) {
        return repository.findByIban(iban).orElse(null);
    }

    @Override
    public void deposit(String iban, double amount) {
        CurrentAccount account = findByIban(iban);
        account.deposit(amount);
        repository.save(account);
        transactionRecorderPort.record(iban, "DEPOSIT", amount);
    }

    @Override
    public void withdraw(String iban, double amount) {
        CurrentAccount account = findByIban(iban);
        account.withdraw(amount);
        repository.save(account);
        transactionRecorderPort.record(iban, "WITHDRAWAL", amount);
    }
}
