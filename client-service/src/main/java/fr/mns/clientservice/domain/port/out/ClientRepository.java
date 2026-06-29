package fr.mns.clientservice.domain.port.out;

import fr.mns.clientservice.domain.model.Client;
import java.util.Optional;

public interface ClientRepository {
    Client save(Client client);
    Optional<Client> findById(String id);
}
