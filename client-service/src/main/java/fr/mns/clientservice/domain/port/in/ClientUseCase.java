package fr.mns.clientservice.domain.port.in;

import fr.mns.clientservice.domain.model.Client;

public interface ClientUseCase {
    Client create(String firstName, String lastName, String email, String phone);
    Client findById(String id);
}
