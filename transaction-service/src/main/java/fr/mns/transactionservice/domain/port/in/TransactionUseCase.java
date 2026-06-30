package fr.mns.transactionservice.domain.port.in;

import fr.mns.transactionservice.domain.model.Transaction;
import fr.mns.transactionservice.domain.model.TransactionType;
import java.util.List;

public interface TransactionUseCase {
    Transaction record(String iban, TransactionType type, double amount);
    List<Transaction> findByIban(String iban);
}
