package com.example.bank.adapter.in.rest;

import com.example.bank.application.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/accounts")
@RestController
public class AccountController2 {
    private final AccountService accountService;

    public AccountController2(AccountService accountService) {
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
