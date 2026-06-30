package fr.mns.accountservice.adapter.in.rest;

import fr.mns.accountservice.application.exceptions.AccountNotFoundException;
import fr.mns.accountservice.application.exceptions.InvalidAmountException;
import fr.mns.accountservice.domain.model.CurrentAccount;
import fr.mns.accountservice.domain.port.in.CurrentAccountUseCase;
import fr.mns.accountservice.domain.port.out.CurrentAccountRepository;

import java.util.Optional;

public class CurrentAccountAdapterIn implements CurrentAccountUseCase {
    private final CurrentAccountRepository repository;

    public CurrentAccountAdapterIn(CurrentAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public CurrentAccount create(String iban, String clientId, double initialBalance, double overdraft) {
        CurrentAccount account = new CurrentAccount(iban, clientId, initialBalance, overdraft);
        return repository.save(account);
    }

    @Override
    public Optional<CurrentAccount> findByIban(String iban) {
        return repository.findByIban(iban);
    }

    @Override
    public void deposit(String iban, double amount) {
        CurrentAccount account = findByIban(iban)
                .orElseThrow(AccountNotFoundException::new);

        if (amount < 0)
            throw new InvalidAmountException();

        account.deposit(amount);
        repository.save(account);
    }

    @Override
    public void withdraw(String iban, double amount) {
        CurrentAccount account = findByIban(iban)
                .orElseThrow(AccountNotFoundException::new);

        account.withdraw(amount);

        if (account.getBalance() - amount < account.getOverdraft())
            throw new InvalidAmountException();

        repository.save(account);
    }
}
