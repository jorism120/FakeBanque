package fr.mns.accountservice.adapter.out.persistence;

import fr.mns.accountservice.adapter.out.persistence.mapper.TransactionAdapterOutMapper;
import fr.mns.accountservice.adapter.out.persistence.repository.TransactionEntityRepository;
import fr.mns.accountservice.domain.model.Transaction;
import fr.mns.accountservice.domain.port.out.TransactionRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TransactionAdapterOut implements TransactionRepository {

    private final TransactionEntityRepository transactionEntityRepository;
    private final TransactionAdapterOutMapper transactionAdapterOutMapper;

    public TransactionAdapterOut(TransactionEntityRepository transactionEntityRepository, TransactionAdapterOutMapper transactionAdapterOutMapper) {
        this.transactionEntityRepository = transactionEntityRepository;
        this.transactionAdapterOutMapper = transactionAdapterOutMapper;
    }

    /**
     * Persiste une transaction.
     */
    @Override
    public Transaction save(Transaction transaction) {
        var entity = transactionAdapterOutMapper.mapToEntity(transaction);
        var savedEntity = transactionEntityRepository.save(entity);
        return transactionAdapterOutMapper.mapToDomain(savedEntity);
    }

    /**
     * Récupère toutes les transactions d'un compte par son IBAN.
     */
    @Override
    public List<Transaction> findByIban(String iban) {
        return transactionEntityRepository.findByIban(iban)
                .stream()
                .map(transactionAdapterOutMapper::mapToDomain)
                .toList();
    }
}
