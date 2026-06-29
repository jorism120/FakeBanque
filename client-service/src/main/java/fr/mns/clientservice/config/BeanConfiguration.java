package fr.mns.clientservice.config;

import fr.mns.clientservice.adapter.in.rest.ClientAdapterIn;
import fr.mns.clientservice.domain.port.in.ClientUseCase;
import fr.mns.clientservice.domain.port.out.ClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ClientUseCase useCase(ClientRepository repository) {
        return new ClientAdapterIn(repository);
    }
}
