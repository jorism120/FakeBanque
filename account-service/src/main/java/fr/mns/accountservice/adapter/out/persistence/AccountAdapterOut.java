package fr.mns.accountservice.adapter.out.persistence;

import fr.mns.accountservice.adapter.out.persistence.mapper.AccountAdapterOutMapper;
import fr.mns.accountservice.adapter.out.persistence.repository.AccountEntityRepository;
import fr.mns.accountservice.domain.model.Account;

import java.io.IOException;
import java.util.List;

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
}


