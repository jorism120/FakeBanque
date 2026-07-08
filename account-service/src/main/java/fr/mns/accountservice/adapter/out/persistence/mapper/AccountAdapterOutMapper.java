package fr.mns.accountservice.adapter.out.persistence.mapper;

import fr.mns.accountservice.adapter.out.persistence.entity.AccountEntity;
import fr.mns.accountservice.domain.model.Account;
import fr.mns.accountservice.domain.model.CurrentAccount;

public class AccountAdapterOutMapper {

    /**
     * Fait le pont entre les entités du domaine et en BDD.
     */
    public Account toDomain(AccountEntity entity) {
        if (entity == null) {
            return null;
        }

        switch (entity.getType()) {
            case CURRENT:
                return CurrentAccount.rehydrate(
                        entity.getIban(),
                        entity.getClientId(),
                        entity.getBalance(),
                        entity.getOverdraft() != null ? entity.getOverdraft() : 0.0
                );

//            case HOUSING_SAVINGS_PLAN:
//                return new HousingSavingsPlan(
//                        entity.getIban(),
//                        entity.getClientId(),
//                        entity.getBalance(),
//                        entity.getStatus()
//                );

            default:
                throw new IllegalArgumentException("Type de compte inconnu : " + entity.getType());
        }
    }
}
