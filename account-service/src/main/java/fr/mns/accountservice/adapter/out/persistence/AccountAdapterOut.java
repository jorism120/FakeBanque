package fr.mns.accountservice.adapter.out.persistence;

import fr.mns.accountservice.adapter.out.persistence.entity.AccountEntity;
import fr.mns.accountservice.adapter.out.persistence.entity.AccountType;
import fr.mns.accountservice.adapter.out.persistence.mapper.AccountAdapterOutMapper;
import fr.mns.accountservice.adapter.out.persistence.repository.AccountEntityRepository;
import fr.mns.accountservice.domain.model.Account;
import fr.mns.accountservice.domain.model.CurrentAccount;
import fr.mns.accountservice.domain.model.HousingSavingsPlan;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountAdapterOut {

    private final AccountEntityRepository accountEntityRepository;
    private final AccountAdapterOutMapper accountAdapterOutMapper;


    public AccountAdapterOut(AccountEntityRepository accountEntityRepository, AccountAdapterOutMapper accountAdapterOutMapper) {
        this.accountEntityRepository = accountEntityRepository;
        this.accountAdapterOutMapper = accountAdapterOutMapper;
    }


    /**
     * Retourne l'ensemble des comptes existants
     */
    public List<Account> findAll() throws IOException {
        return accountEntityRepository.findAll().stream()
                .map(accountAdapterOutMapper::toDomain)
                .toList();
    }

    /**
     * Retourne l'ensembles des comptes associés à un client
     */
    public List<Account> getAccounts(String clientId) {
        return accountEntityRepository.findByClientId(clientId).stream()
                .map(accountAdapterOutMapper::toDomain)
                .toList();
    }
}


