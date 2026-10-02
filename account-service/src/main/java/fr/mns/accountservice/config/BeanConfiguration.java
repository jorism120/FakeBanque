package fr.mns.accountservice.config;

import fr.mns.accountservice.adapter.in.rest.CurrentAccountAdapterIn;
import fr.mns.accountservice.domain.port.in.CurrentAccountUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CurrentAccountAdapterIn adapter(CurrentAccountUseCase useCase) {
        return new CurrentAccountAdapterIn(useCase);
    }
}
