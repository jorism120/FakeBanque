package fr.mns.accountservice.config;

import fr.mns.accountservice.adapter.in.rest.CurrentAccountAdapterIn;
import fr.mns.accountservice.domain.port.in.CurrentAccountUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
     @Bean
    public CurrentAccountAdapterIn adapter(
            CurrentAccountUseCase useCase) {

        return new CurrentAccountAdapterIn(useCase);
    }
}
