package fr.mns.accountservice.domain.port.out;

import fr.mns.accountservice.domain.model.Transaction;
import java.util.List;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    List<Transaction> findByIban(String iban);
}
