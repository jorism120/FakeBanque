package com.exerice.fakebanque.service;

import com.exerice.fakebanque.repository.CurrentAccountRepository;
import com.exerice.fakebanque.repository.HousingSavingPlanRepository;
import com.exerice.fakebanque.repository.SavingsAccountRepository;

import java.io.IOException;

public class AccountService {
    private final CurrentAccountRepository currentAccountRepository;
    private final HousingSavingPlanRepository housingSavingPlanRepository;
    private final SavingsAccountRepository savingsAccountRepository;

    public AccountService(CurrentAccountRepository currentAccountRepository,
                          HousingSavingPlanRepository housingSavingPlanRepository,
                          SavingsAccountRepository savingsAccountRepository)
    {
        this.currentAccountRepository = currentAccountRepository;
        this.housingSavingPlanRepository = housingSavingPlanRepository;
        this.savingsAccountRepository = savingsAccountRepository;
    }

    public int getAllSoldFromHousingSavingsPlan ()
    {
        return housingSavingPlanRepository.getSold();
    }

    public int getAllSoldFromSavingsAccount()
    {
        return savingsAccountRepository.getSold();
    }

    public void CreditCurrentAccount(double sum, String iban) throws IOException {
        currentAccountRepository.CreditCurrentAccount(sum, iban);
    }

    public void DebitCurrentAccount(double sum, String iban) throws IOException {
        currentAccountRepository.DebitCurrentAccount(sum, iban);
    }
}


