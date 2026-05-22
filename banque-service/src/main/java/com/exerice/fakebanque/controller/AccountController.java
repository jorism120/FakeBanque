package com.exerice.fakebanque.controller;

import com.exerice.fakebanque.model.account.Account;
import com.exerice.fakebanque.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/accounts")
@RestController
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/savingsAccount/{iban}/sold")
    public int getAccountsSoldFromSavingsAccount(@PathVariable String iban) {
        return accountService.getAllSoldFromSavingsAccount();
    }
    @GetMapping("/housingAccount/{iban}/sold")
    public int getAccountsSoldFromHousingAccount(@PathVariable String iban) {
        return accountService.getAllSoldFromHousingSavingsPlan();
    }

    @PostMapping("/currentAccount/{iban}/credit")
    public String CreditCurrentAccount(@PathVariable String iban) {
        return "Compte crédité de ...";
    }
}
