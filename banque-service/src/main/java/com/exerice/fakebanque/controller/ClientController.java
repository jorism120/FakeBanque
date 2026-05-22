package com.exerice.fakebanque.controller;

import com.exerice.fakebanque.model.account.Account;
import com.exerice.fakebanque.service.ClientService;
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