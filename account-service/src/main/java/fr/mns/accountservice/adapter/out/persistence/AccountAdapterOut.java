package fr.mns.accountservice.adapter.out.persistence;

import fr.mns.accountservice.domain.model.Account;
import fr.mns.accountservice.domain.model.CurrentAccount;
import fr.mns.accountservice.domain.model.HousingSavingsPlan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountAdapterOut {
    public List<Account> findAll() throws IOException {
        List<Account> accounts = new ArrayList<>();

        CurrentAccount currentAccount = new CurrentAccount("FR458512369", "1", 150, -50);
        HousingSavingsPlan housingSavingsPlan = new HousingSavingsPlan("FR8964268", "3", 1695.30, true);
        accounts.add(currentAccount);
        accounts.add(housingSavingsPlan);

        return accounts;
    }


    public List<Account> getAccounts(String clientId){
        List<Account> accounts = new ArrayList<>();
        CurrentAccount currentAccount = new CurrentAccount("12485FB851TID584KJDUH5698598", clientId, 539.3, -150);
        HousingSavingsPlan housingSavingsPlan = new HousingSavingsPlan("12485FB851TID584KJDUH5698599", clientId, 8600, false);
        accounts.add(currentAccount);
        accounts.add(housingSavingsPlan);

        return accounts;
    }
}
