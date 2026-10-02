package fr.mns.clientservice.adapter.out.persistence;

import fr.mns.clientservice.adapter.out.persistence.mapper.ClientAdapterOutMapper;
import fr.mns.clientservice.adapter.out.persistence.repository.ClientEntityRepository;
import fr.mns.clientservice.domain.model.Client;
import fr.mns.clientservice.domain.port.out.ClientRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientAdapterOut implements ClientRepository {

    private final ClientEntityRepository clientEntityRepository;
    private final ClientAdapterOutMapper clientAdapterOutMapper;

    public ClientAdapterOut(ClientEntityRepository clientEntityRepository, ClientAdapterOutMapper clientAdapterOutMapper) {
        this.clientEntityRepository = clientEntityRepository;
        this.clientAdapterOutMapper = clientAdapterOutMapper;
    }

    /**
     * Persiste un client.
     */
    @Override
    public Client save(Client client) {
        var entity = clientAdapterOutMapper.mapToEntity(client);
        var savedEntity = clientEntityRepository.save(entity);
        return clientAdapterOutMapper.mapToDomain(savedEntity);
    }

    /**
     * Cherche un client par son identifiant.
     */
    @Override
    public Optional<Client> findById(String id) {
        return clientEntityRepository.findById(id)
                .map(clientAdapterOutMapper::mapToDomain);
    }

    /**
     * Cherche un client par son email.
     */
    @Override
    public Optional<Client> findByEmail(String email) {
        return clientEntityRepository.findByEmail(email)
                .map(clientAdapterOutMapper::mapToDomain);
    }
}
