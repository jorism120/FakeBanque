package fr.mns.accountservice.application.service;

import fr.mns.accountservice.application.exception.AccountAlreadyExistsException;
import fr.mns.accountservice.application.exception.AccountNotFoundException;
import fr.mns.accountservice.application.exception.ForbiddenException;
import fr.mns.accountservice.domain.model.CurrentAccount;
import fr.mns.accountservice.domain.port.in.CurrentAccountUseCase;
import fr.mns.accountservice.domain.port.out.CurrentAccountRepository;
import fr.mns.accountservice.domain.port.out.TransactionRecorderPort;
import org.springframework.stereotype.Service;

@Service
public class CurrentAccountService implements CurrentAccountUseCase {
    private final CurrentAccountRepository repository;
    private final TransactionRecorderPort transactionRecorderPort;

    public CurrentAccountService(CurrentAccountRepository repository, TransactionRecorderPort transactionRecorderPort) {
        this.repository = repository;
        this.transactionRecorderPort = transactionRecorderPort;
    }

    @Override
    public CurrentAccount create(String iban, String clientId, double initialBalance, double overdraft) {
        if(repository.findByIban(iban).isPresent()){
            throw new AccountAlreadyExistsException();
        }

        CurrentAccount account = new CurrentAccount(iban, clientId, initialBalance, overdraft);
        return repository.save(account);
    }

    @Override
    public CurrentAccount getAccount(String iban, String clientId) {
        CurrentAccount account =
                repository.findByIban(iban)
                        .orElseThrow(AccountNotFoundException::new);

        if(!account.getClientId().equals(clientId)){
            throw new ForbiddenException("Vous n'avez pas accès à ce compte.");
        }

        return account;
    }

    @Override
    public void deposit(String iban, String clientId, double amount) {
        CurrentAccount account = getAccount(iban, clientId);
        account.deposit(amount);
        repository.save(account);
        transactionRecorderPort.record(iban, "DEPOSIT", amount);
    }

    @Override
    public void withdraw(String iban, String clientId, double amount) {
        CurrentAccount account = getAccount(iban, clientId);
        account.withdraw(amount);
        repository.save(account);
        transactionRecorderPort.record(iban, "WITHDRAWAL", amount);
    }
}
