package fr.mns.accountservice.domain.port.in;

import fr.mns.accountservice.domain.model.CurrentAccount;

import java.util.List;

public interface CurrentAccountUseCase {
    CurrentAccount create(String iban, String clientId, double balance, double overdraft);

    List<CurrentAccount> getAccounts(String clientId);

    CurrentAccount getAccount(String iban, String clientId);

    void deposit(String iban, String clientId, double amount);

    void withdraw(String iban, String clientId, double amount);
}
