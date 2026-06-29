package fr.mns.accountservice.adapter.in.rest;

import fr.mns.accountservice.domain.model.Transaction;
import fr.mns.accountservice.domain.port.in.TransactionUseCase;
import fr.mns.accountservice.domain.port.out.TransactionRepository;
import java.util.List;

public class TransactionAdapterIn implements TransactionUseCase {
    private final TransactionRepository repository;

    public TransactionAdapterIn(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Transaction> findByIban(String iban) {
        return repository.findByIban(iban);
    }
}
