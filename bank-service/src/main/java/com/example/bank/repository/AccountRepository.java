package com.example.bank.repository;

import com.example.bank.model.account.Account;
import com.example.bank.model.account.CurrentAccount;
import com.example.bank.model.account.HousingSavingsPlan;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepository {
    private static final String FILE_PATH = "data/accounts.json";

    private final ObjectMapper mapper = new ObjectMapper();

    public List<Account> findAll() throws IOException {

        return mapper.readValue(
                new File(FILE_PATH),
                new TypeReference<List<Account>>() {}
        );
    }

    public void saveAll(List<Account> accounts)
            throws IOException {

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(FILE_PATH), accounts);
    }

    public Optional<Account> findByIban(String iban) throws IOException {
        return findAll().stream()
                .filter(a -> a.getIban().equals(iban))
                .findFirst();
    }

    public List<Account> getAccounts(int clientId){
        List<Account> accounts = new ArrayList<>();
        CurrentAccount currentAccount = new CurrentAccount("12485FB851TID584KJDUH5698598", 539.3, -150);
        HousingSavingsPlan housingSavingsPlan = new HousingSavingsPlan("12485FB851TID584KJDUH5698599", 8600, false);
        accounts.add(currentAccount);
        accounts.add(housingSavingsPlan);

        return accounts;
    }
}
