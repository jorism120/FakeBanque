package com.example.bank.application.service;

import com.example.bank.domain.model.account.Account;
import com.example.bank.domain.port.out.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientService imple{

    private ClientRepository clientRepository;

    public ClientService ()
    {
        clientRepository = new ClientRepository();
    }

    public List<Account> getAccounts(int clientId) {
        return clientRepository.getAccounts(clientId);
    }
}
