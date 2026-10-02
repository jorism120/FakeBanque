package fr.mns.clientservice.domain.port.in;

import fr.mns.clientservice.domain.model.Client;

public interface ClientUseCase {
    Client register(String firstName, String lastName, String email, String password);
    Client findById(String id);
    Client authenticate(String email, String password);
}
