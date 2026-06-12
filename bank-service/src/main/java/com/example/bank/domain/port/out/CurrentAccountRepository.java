package com.example.bank.domain.port.out;


import com.example.bank.domain.model.account.Account;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class CurrentAccountRepository {
    private final AccountRepository accountRepository =
            new AccountRepository();

    public void CreditCurrentAccount(double sum, String iban) throws IOException {
        // Lecture des comptes
        List<Account> accounts = accountRepository.findAll();

        // Recherche du compte
        Account account = accounts.stream()
                .filter(a -> a.getIban().equals(iban))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(
                                "Compte introuvable"));

        // Crédit du compte
        account.setBalance(
                account.getBalance() + sum
        );

        // Sauvegarde du JSON
        accountRepository.saveAll(accounts);

        System.out.println(
                "Compte crédité : "
                        + account.getBalance()
        );
    }

    public void DebitCurrentAccount(double sum, String iban) throws IOException {
    // Lecture des comptes
        List<Account> accounts = accountRepository.findAll();

        // Recherche du compte
        Account account = accounts.stream()
                .filter(a -> a.getIban().equals(iban))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(
                                "Compte introuvable"));

        // Crédit du compte
        account.setBalance(
                account.getBalance() - sum
        );

        // Sauvegarde du JSON
        accountRepository.saveAll(accounts);

        System.out.println(
                "Compte crédité : "
                        + account.getBalance()
        );
    }
}
