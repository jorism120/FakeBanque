package com.example.bank.application.service;

import com.example.bank.domain.port.in.AccountUseCase;
import com.example.bank.domain.port.out.CurrentAccountRepository;
import com.example.bank.domain.port.out.HousingSavingPlanRepository;
import com.example.bank.domain.port.out.SavingsAccountRepository;
import org.springframework.stereotype.Service;


import java.io.IOException;

@Service
public class AccountService implements AccountUseCase {
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

    @Override
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


