package com.exerice.fakebanque.service;

import com.exerice.fakebanque.model.account.Account;
import com.exerice.fakebanque.repository.ClientRepository;

import java.util.List;

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
