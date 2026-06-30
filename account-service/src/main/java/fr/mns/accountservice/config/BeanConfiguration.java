package fr.mns.accountservice.config;

import fr.mns.accountservice.adapter.in.rest.CurrentAccountAdapterIn;
import fr.mns.accountservice.domain.port.in.CurrentAccountUseCase;
import fr.mns.accountservice.domain.port.out.CurrentAccountRepository;
import fr.mns.accountservice.domain.port.out.TransactionRecorderPort;
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
    public CurrentAccountUseCase useCase(CurrentAccountRepository repository, TransactionRecorderPort transactionRecorderPort) {
        return new CurrentAccountAdapterIn(repository, transactionRecorderPort);
    }
}
