package fr.mns.clientservice.config;

import fr.mns.clientservice.adapter.in.rest.ClientAdapterIn;
import fr.mns.clientservice.domain.port.in.ClientUseCase;
import fr.mns.clientservice.domain.port.out.ClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ClientUseCase useCase(ClientRepository repository, PasswordEncoder passwordEncoder) {
        return new ClientAdapterIn(repository, passwordEncoder);
    }
}
