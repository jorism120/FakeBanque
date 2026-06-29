package fr.mns.accountservice.config;

import fr.mns.accountservice.adapter.in.rest.CurrentAccountAdapterIn;
import fr.mns.accountservice.adapter.in.rest.TransactionAdapterIn;
import fr.mns.accountservice.domain.port.in.CurrentAccountUseCase;
import fr.mns.accountservice.domain.port.in.TransactionUseCase;
import fr.mns.accountservice.domain.port.out.CurrentAccountRepository;
import fr.mns.accountservice.domain.port.out.TransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CurrentAccountUseCase useCase(CurrentAccountRepository repository, TransactionRepository transactionRepository) {
        return new CurrentAccountAdapterIn(repository, transactionRepository);
    }

    @Bean
    public TransactionUseCase transactionUseCase(TransactionRepository transactionRepository) {
        return new TransactionAdapterIn(transactionRepository);
    }
}
