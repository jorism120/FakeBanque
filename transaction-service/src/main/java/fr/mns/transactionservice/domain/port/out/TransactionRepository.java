package fr.mns.transactionservice.domain.port.out;

import fr.mns.transactionservice.domain.model.Transaction;
import java.util.List;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    List<Transaction> findByIban(String iban);
}
