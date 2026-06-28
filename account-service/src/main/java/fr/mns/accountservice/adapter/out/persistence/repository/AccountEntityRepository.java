package fr.mns.accountservice.adapter.out.persistence.repository;

import fr.mns.accountservice.adapter.out.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AccountEntityRepository extends JpaRepository <AccountEntity,String> {
    List<AccountEntity> findByClientId(String clientId);
}
