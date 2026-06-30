package fr.mns.transactionservice.config;

import fr.mns.transactionservice.adapter.in.rest.TransactionAdapterIn;
import fr.mns.transactionservice.domain.port.in.TransactionUseCase;
import fr.mns.transactionservice.domain.port.out.TransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public TransactionUseCase useCase(TransactionRepository repository) {
        return new TransactionAdapterIn(repository);
    }
}
