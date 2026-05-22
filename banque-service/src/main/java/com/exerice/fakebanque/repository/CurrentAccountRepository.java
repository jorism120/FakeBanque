package com.exerice.fakebanque.repository;

import com.exerice.fakebanque.model.account.Account;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
