package fr.mns.accountservice.adapter.out.persistence.mapper;

import fr.mns.accountservice.adapter.out.persistence.entity.AccountEntity;
import fr.mns.accountservice.adapter.out.persistence.entity.AccountType;
import fr.mns.accountservice.domain.model.CurrentAccount;
import org.springframework.stereotype.Component;

@Component
public class CurrentAccountAdapterOutMapper {
    /**
     * Fait le pont entre le entités du domaine et en BDD.
     */
    public AccountEntity mapToEntity(CurrentAccount account) {
        AccountEntity entity = new AccountEntity();
        entity.setIban(account.getIban());
        entity.setClientId(account.getClientId());
        entity.setBalance(account.getBalance());
        entity.setType(AccountType.CURRENT);
        entity.setOverdraft(account.getOverdraft());
        entity.setTaxed(null);
        return entity;
    }

    /**
     * Fait le pont entre le entités du domaine et en BDD.
     */
    public CurrentAccount mapToDomain(AccountEntity entity) {
        return new CurrentAccount(
                entity.getIban(),
                entity.getClientId(),
                entity.getBalance(),
                entity.getOverdraft() != null ? entity.getOverdraft() : 0.0
        );
    }


}
