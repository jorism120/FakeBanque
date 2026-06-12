package com.example.bank.model;


import com.example.bank.model.account.Account;

import java.util.List;

public class Bank {
    private String name;
    private String bic;
    private List<Account> accounts;

    public Bank(String name, String bic, List<Account> accounts) {
        this.name = name;
        this.bic = bic;
        this.accounts = accounts;
    }
}
