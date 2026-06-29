package fr.mns.accountservice.adapter.out.persistence.repository;

import fr.mns.accountservice.adapter.out.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionEntityRepository extends JpaRepository<TransactionEntity, String> {
    List<TransactionEntity> findByIban(String iban);
}
