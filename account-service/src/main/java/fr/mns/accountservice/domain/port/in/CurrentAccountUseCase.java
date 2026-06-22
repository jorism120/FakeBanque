package fr.mns.accountservice.domain.port.in;

import fr.mns.accountservice.domain.model.CurrentAccount;

public interface CurrentAccountUseCase {
    CurrentAccount create(String iban, String clientId, double balance, double overdraft);

    CurrentAccount findByIban(String iban);

    void deposit(String iban, double amount);

    void withdraw(String iban, double amount);
}
