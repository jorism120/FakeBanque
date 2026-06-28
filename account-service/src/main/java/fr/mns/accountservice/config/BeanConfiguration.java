package fr.mns.accountservice.config;

import fr.mns.accountservice.adapter.in.rest.CurrentAccountAdapterIn;
import fr.mns.accountservice.adapter.out.persistence.CurrentAccountAdapterOut;
import fr.mns.accountservice.domain.port.in.CurrentAccountUseCase;
import fr.mns.accountservice.domain.port.out.CurrentAccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public CurrentAccountUseCase useCase(
            CurrentAccountRepository repository) {
        return new CurrentAccountAdapterIn(repository);
    }
}
