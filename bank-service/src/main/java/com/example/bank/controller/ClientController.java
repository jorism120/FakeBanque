package com.example.bank.controller;

import com.example.bank.model.account.Account;
import com.example.bank.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService _clientService) {
        this.clientService = _clientService;
    }

    @GetMapping("/{id}/accounts")
    public List<Account> getClientAccounts(@PathVariable int id) {
        return clientService.getAccounts(id);
    }
}