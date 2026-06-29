package fr.mns.clientservice.controller;

import fr.mns.clientservice.domain.model.Client;
import fr.mns.clientservice.domain.port.in.ClientUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientUseCase useCase;

    public ClientController(ClientUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ClientDto createClient(@RequestBody CreateClientRequest request, @AuthenticationPrincipal Jwt jwt) {
        if (request.firstName() == null || request.firstName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le prénom est obligatoire");
        }
        if (request.lastName() == null || request.lastName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le nom est obligatoire");
        }

        Client client = useCase.create(request.firstName(), request.lastName(), request.email(), request.phone());
        return new ClientDto(client.getId(), client.getFirstName(), client.getLastName(), client.getEmail(), client.getPhone());
    }

    @GetMapping("/{id}")
    public ClientDto getClient(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        Client client = useCase.findById(id);

        if (client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le client demandé n'existe pas");
        }

        return new ClientDto(client.getId(), client.getFirstName(), client.getLastName(), client.getEmail(), client.getPhone());
    }
}

record CreateClientRequest(
        String firstName,
        String lastName,
        String email,
        String phone
) {
}

record ClientDto(
        String id,
        String firstName,
        String lastName,
        String email,
        String phone
) {
}
