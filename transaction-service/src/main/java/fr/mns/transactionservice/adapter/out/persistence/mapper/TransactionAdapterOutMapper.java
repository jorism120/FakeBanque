package fr.mns.transactionservice.adapter.out.persistence.mapper;

import fr.mns.transactionservice.adapter.out.persistence.entity.TransactionEntity;
import fr.mns.transactionservice.domain.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionAdapterOutMapper {

    /**
     * Fait le pont entre les entités du domaine et en BDD.
     */
    public TransactionEntity mapToEntity(Transaction transaction) {
        TransactionEntity entity = new TransactionEntity();
        entity.setId(transaction.getId());
        entity.setIban(transaction.getIban());
        entity.setType(transaction.getType());
        entity.setAmount(transaction.getAmount());
        entity.setTimestamp(transaction.getTimestamp());
        return entity;
    }

    /**
     * Fait le pont entre les entités du domaine et en BDD.
     */
    public Transaction mapToDomain(TransactionEntity entity) {
        return new Transaction(
                entity.getId(),
                entity.getIban(),
                entity.getType(),
                entity.getAmount(),
                entity.getTimestamp()
        );
    }
}
