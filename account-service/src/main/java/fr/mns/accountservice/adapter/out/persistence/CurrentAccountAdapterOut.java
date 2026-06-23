package fr.mns.accountservice.adapter.out.persistence;

import fr.mns.accountservice.domain.model.Account;
import fr.mns.accountservice.domain.model.CurrentAccount;
import fr.mns.accountservice.domain.port.out.CurrentAccountRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CurrentAccountAdapterOut implements CurrentAccountRepository {
    private final Map<String, CurrentAccount> accounts =
            new HashMap<>();

    @Override
    public CurrentAccount save(
            CurrentAccount account) {

        accounts.put(account.getIban(), account);

        return account;
    }

    @Override
    public Optional<CurrentAccount> findByIban(String iban) {
        CurrentAccount account = new CurrentAccount("FR7884452", "1", 569, -120);
        return Optional.of(account);
        //return Optional.ofNullable(accounts.get(iban));
    }
}
