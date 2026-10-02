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

    @PostMapping("/register")
    public ClientDto register(@RequestBody RegisterClientRequest request) {
        if (request.firstName() == null || request.firstName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le prénom est obligatoire");
        }
        if (request.lastName() == null || request.lastName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le nom est obligatoire");
        }
        if (request.email() == null || request.email().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'email est obligatoire");
        }
        if (request.password() == null || request.password().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le mot de passe est obligatoire");
        }

        Client client = useCase.register(request.firstName(), request.lastName(), request.email(), request.password());
        return toDto(client);
    }

    @PostMapping("/authenticate")
    public ClientDto authenticate(@RequestBody AuthenticateRequest request) {
        Client client = useCase.authenticate(request.email(), request.password());

        if (client == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou mot de passe invalide");
        }

        return toDto(client);
    }

    @GetMapping("/me")
    public ClientDto getCurrentClient(@AuthenticationPrincipal Jwt jwt) {
        Client client = useCase.findById(jwt.getClaimAsString("clientId"));

        if (client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le client demandé n'existe pas");
        }

        return toDto(client);
    }

    @GetMapping("/{id}")
    public ClientDto getClient(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        Client client = useCase.findById(id);

        if (client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le client demandé n'existe pas");
        }

        return toDto(client);
    }

    private ClientDto toDto(Client client) {
        return new ClientDto(client.getId(), client.getFirstName(), client.getLastName(), client.getEmail());
    }
}

record RegisterClientRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {
}

record AuthenticateRequest(
        String email,
        String password
) {
}

record ClientDto(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
