package fr.mns.accountservice.adapter.out.persistence;
import fr.mns.accountservice.adapter.out.persistence.entity.AccountEntity;
import fr.mns.accountservice.adapter.out.persistence.entity.AccountType;
import fr.mns.accountservice.adapter.out.persistence.mapper.CurrentAccountAdapterOutMapper;
import fr.mns.accountservice.adapter.out.persistence.repository.AccountEntityRepository;
import fr.mns.accountservice.domain.model.CurrentAccount;
import fr.mns.accountservice.domain.port.out.CurrentAccountRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CurrentAccountAdapterOut implements CurrentAccountRepository {

    private final AccountEntityRepository accountEntityRepository;
    private final CurrentAccountAdapterOutMapper currentAccountAdapterOutMapper;

    public CurrentAccountAdapterOut(AccountEntityRepository accountEntityRepository, CurrentAccountAdapterOutMapper currentAccountAdapterOutMapper) {
        this.currentAccountAdapterOutMapper = currentAccountAdapterOutMapper;
        this.accountEntityRepository = accountEntityRepository;
    }

    /**
     * Persiste un compte.
     */
    @Override
    public CurrentAccount save(CurrentAccount account) {
        AccountEntity entity = currentAccountAdapterOutMapper.mapToEntity(account);
        AccountEntity savedEntity = accountEntityRepository.save(entity);
        return currentAccountAdapterOutMapper.mapToDomain(savedEntity);
    }

    /**
     * Cherche un compte par son IBAN
     */
    @Override
    public Optional<CurrentAccount> findByIban(String iban) {
        return accountEntityRepository.findById(iban)
                .filter(entity -> entity.getType() == AccountType.CURRENT)
                .map(currentAccountAdapterOutMapper::mapToDomain);
    }

    @Override
    public List<CurrentAccount> findByClientId(String clientId) {
        return accountEntityRepository.findByClientId(clientId)
                .stream()
                .filter(entity -> entity.getType() == AccountType.CURRENT)
                .map(currentAccountAdapterOutMapper::mapToDomain)
                .toList();
    }
}
