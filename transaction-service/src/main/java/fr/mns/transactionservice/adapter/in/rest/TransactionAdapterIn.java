package fr.mns.transactionservice.adapter.in.rest;

import fr.mns.transactionservice.domain.model.Transaction;
import fr.mns.transactionservice.domain.model.TransactionType;
import fr.mns.transactionservice.domain.port.in.TransactionUseCase;
import fr.mns.transactionservice.domain.port.out.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TransactionAdapterIn implements TransactionUseCase {
    private final TransactionRepository repository;

    public TransactionAdapterIn(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transaction record(String iban, TransactionType type, double amount) {
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), iban, type, amount, LocalDateTime.now());
        return repository.save(transaction);
    }

    @Override
    public List<Transaction> findByIban(String iban) {
        return repository.findByIban(iban);
    }
}
