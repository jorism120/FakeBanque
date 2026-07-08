package fr.mns.clientservice.adapter.in.rest;

import fr.mns.clientservice.application.exception.ClientAlreadyExistsException;
import fr.mns.clientservice.domain.model.Client;
import fr.mns.clientservice.domain.port.in.ClientUseCase;
import fr.mns.clientservice.domain.port.out.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class ClientAdapterIn implements ClientUseCase {
    private final ClientRepository repository;
    private final PasswordEncoder passwordEncoder;

    public ClientAdapterIn(ClientRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Client register(String firstName, String lastName, String email, String password) {
        if (repository.findByEmail(email).isPresent()) {
            throw new ClientAlreadyExistsException();
        }

        Client client = new Client(UUID.randomUUID().toString(), firstName, lastName, email, passwordEncoder.encode(password));
        return repository.save(client);
    }

    @Override
    public Client findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Client authenticate(String email, String password) {
        return repository.findByEmail(email)
                .filter(client -> passwordEncoder.matches(password, client.getPassword()))
                .orElse(null);
    }
}
