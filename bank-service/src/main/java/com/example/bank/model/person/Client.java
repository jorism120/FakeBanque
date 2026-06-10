package com.example.bank.model.person;

import com.example.bank.model.account.Account;

import java.util.List;

public class Client extends Person {
    private List<Account> accounts;

    public Client(String address, String firstname, String name, int id, List<Account> accounts) {
        super(address, firstname, name, id);
        this.accounts = accounts;
    }
}
