package fr.mns.clientservice.adapter.in.rest;

import fr.mns.clientservice.domain.model.Client;
import fr.mns.clientservice.domain.port.in.ClientUseCase;
import fr.mns.clientservice.domain.port.out.ClientRepository;

import java.util.UUID;

public class ClientAdapterIn implements ClientUseCase {
    private final ClientRepository repository;

    public ClientAdapterIn(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Client create(String firstName, String lastName, String email, String phone) {
        Client client = new Client(UUID.randomUUID().toString(), firstName, lastName, email, phone);
        return repository.save(client);
    }

    @Override
    public Client findById(String id) {
        return repository.findById(id).orElse(null);
    }
}
