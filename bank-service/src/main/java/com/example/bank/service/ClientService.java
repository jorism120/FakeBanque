package com.example.bank.service;

import com.example.bank.model.account.Account;
import com.example.bank.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService ()
    {
        clientRepository = new ClientRepository();
    }

    public List<Account> getAccounts(int clientId) {
        return clientRepository.getAccounts(clientId);
    }
}
