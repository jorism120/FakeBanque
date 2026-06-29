package fr.mns.clientservice.adapter.out.persistence.repository;

import fr.mns.clientservice.adapter.out.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientEntityRepository extends JpaRepository<ClientEntity, String> {
}
