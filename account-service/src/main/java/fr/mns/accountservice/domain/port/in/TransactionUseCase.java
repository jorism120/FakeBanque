package fr.mns.accountservice.domain.port.in;

import fr.mns.accountservice.domain.model.Transaction;
import java.util.List;

public interface TransactionUseCase {
    List<Transaction> findByIban(String iban);
}
