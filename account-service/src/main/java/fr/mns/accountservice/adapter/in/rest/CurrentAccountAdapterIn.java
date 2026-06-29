package fr.mns.accountservice.adapter.in.rest;

import fr.mns.accountservice.domain.model.CurrentAccount;
import fr.mns.accountservice.domain.model.Transaction;
import fr.mns.accountservice.domain.model.TransactionType;
import fr.mns.accountservice.domain.port.in.CurrentAccountUseCase;
import fr.mns.accountservice.domain.port.out.CurrentAccountRepository;
import fr.mns.accountservice.domain.port.out.TransactionRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class CurrentAccountAdapterIn implements CurrentAccountUseCase {
    private final CurrentAccountRepository repository;
    private final TransactionRepository transactionRepository;

    public CurrentAccountAdapterIn(CurrentAccountRepository repository, TransactionRepository transactionRepository) {
        this.repository = repository;
        this.transactionRepository = transactionRepository;
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
        transactionRepository.save(new Transaction(UUID.randomUUID().toString(), iban, TransactionType.DEPOSIT, amount, LocalDateTime.now()));
    }

    @Override
    public void withdraw(String iban, double amount) {

        CurrentAccount account = findByIban(iban);
        account.withdraw(amount);

        repository.save(account);
        transactionRepository.save(new Transaction(UUID.randomUUID().toString(), iban, TransactionType.WITHDRAWAL, amount, LocalDateTime.now()));
    }
}
