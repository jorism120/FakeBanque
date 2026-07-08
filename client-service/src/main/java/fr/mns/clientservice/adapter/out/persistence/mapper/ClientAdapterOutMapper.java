package fr.mns.clientservice.adapter.out.persistence.mapper;

import fr.mns.clientservice.adapter.out.persistence.entity.ClientEntity;
import fr.mns.clientservice.domain.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientAdapterOutMapper {

    /**
     * Fait le pont entre les entités du domaine et en BDD.
     */
    public ClientEntity mapToEntity(Client client) {
        ClientEntity entity = new ClientEntity();
        entity.setId(client.getId());
        entity.setFirstName(client.getFirstName());
        entity.setLastName(client.getLastName());
        entity.setEmail(client.getEmail());
        entity.setPassword(client.getPassword());
        return entity;
    }

    /**
     * Fait le pont entre les entités du domaine et en BDD.
     */
    public Client mapToDomain(ClientEntity entity) {
        return new Client(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword()
        );
    }
}
