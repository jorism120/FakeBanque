package fr.mns.accountservice.domain.port.in;

import fr.mns.accountservice.domain.model.CurrentAccount;

import java.util.Optional;

public interface CurrentAccountUseCase {
    CurrentAccount create(String iban, String clientId, double balance, double overdraft);

    Optional<CurrentAccount> findByIban(String iban);

    void deposit(String iban, double amount);

    void withdraw(String iban, double amount);
}
