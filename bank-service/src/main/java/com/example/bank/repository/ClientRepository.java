package com.example.bank.repository;


import com.example.bank.model.account.Account;
import com.example.bank.model.account.CurrentAccount;
import com.example.bank.model.account.HousingSavingsPlan;

import java.util.ArrayList;
import java.util.List;

public class ClientRepository {

    public List<Account> getAccounts(int clientId){
        List<Account> accounts = new ArrayList<>();
        CurrentAccount currentAccount = new CurrentAccount("12485FB851TID584KJDUH5698598", 539.3, -150);
        HousingSavingsPlan housingSavingsPlan = new HousingSavingsPlan("12485FB851TID584KJDUH5698599", 8600, false);
        accounts.add(currentAccount);
        accounts.add(housingSavingsPlan);

        return accounts;
    }
}
