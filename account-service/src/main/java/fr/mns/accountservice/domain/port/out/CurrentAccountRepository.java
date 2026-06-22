package fr.mns.accountservice.domain.port.out;

import fr.mns.accountservice.domain.model.CurrentAccount;

import java.util.Optional;

public interface CurrentAccountRepository {
    CurrentAccount save(CurrentAccount account);

    Optional<CurrentAccount> findByIban(String iban);
}
