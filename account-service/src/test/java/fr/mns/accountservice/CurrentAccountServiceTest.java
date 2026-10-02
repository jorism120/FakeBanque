package fr.mns.accountservice;

import fr.mns.accountservice.application.exception.AccountNotFoundException;
import fr.mns.accountservice.application.exception.ForbiddenException;
import fr.mns.accountservice.application.exception.InvalidAmountException;
import fr.mns.accountservice.application.exception.OverdraftExceededException;
import fr.mns.accountservice.application.service.CurrentAccountService;
import fr.mns.accountservice.domain.model.CurrentAccount;
import fr.mns.accountservice.domain.port.out.CurrentAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CurrentAccountServiceTest {
    @Mock
    private CurrentAccountRepository repository;

    @InjectMocks
    private CurrentAccountService service;

    @Test
    void shouldReturnAccount() {

        CurrentAccount account =
                new CurrentAccount("FR1", "client1", 1000, -500);

        when(repository.findByIban("FR1"))
                .thenReturn(Optional.of(account));

        CurrentAccount result =
                service.getAccount("FR1", "client1");

        assertEquals(account, result);
    }

    @Test
    void shouldThrowAccountNotFound() {

        when(repository.findByIban("FR1"))
                .thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class,
                () -> service.getAccount("FR1", "client1"));
    }

    @Test
    void shouldThrowForbiddenException() {

        CurrentAccount account =
                new CurrentAccount("FR1", "client1", 1000, -500);

        when(repository.findByIban("FR1"))
                .thenReturn(Optional.of(account));

        assertThrows(
                ForbiddenException.class,
                () -> service.getAccount("FR1", "client2"));
    }

    @Test
    void shouldDepositMoney() {

        CurrentAccount account =
                new CurrentAccount("FR1","client",1000,-500);

        account.deposit(200);

        assertEquals(1200, account.checkBalance());
    }

    @Test
    void shouldSaveAccountAfterDeposit() {

        CurrentAccount account =
                new CurrentAccount("FR1", "client1", 1000, -500);

        when(repository.findByIban("FR1"))
                .thenReturn(Optional.of(account));

        service.deposit("FR1", "client1", 500);

        assertEquals(1500, account.checkBalance());

        verify(repository).save(account);
    }

    @Test
    void shouldThrowWhenDepositNegative() {

        CurrentAccount account =
                new CurrentAccount("FR1","client",1000,-500);

        assertThrows(
                InvalidAmountException.class,
                () -> account.deposit(-100));
    }

    @Test
    void shouldWithdrawMoney() {

        CurrentAccount account =
                new CurrentAccount("FR1","client",1000,-500);

        account.withdraw(500);

        assertEquals(500, account.checkBalance());
    }

    @Test
    void shouldSaveAccountAfterWithdraw() {

        CurrentAccount account =
                new CurrentAccount("FR1", "client1", 1000, -500);

        when(repository.findByIban("FR1"))
                .thenReturn(Optional.of(account));

        service.withdraw("FR1", "client1", 300);

        assertEquals(700, account.checkBalance());

        verify(repository).save(account);
    }

    @Test
    void shouldNotSaveWhenWithdrawFails() {

        CurrentAccount account =
                new CurrentAccount("FR1", "client1", 1000, -500);

        when(repository.findByIban("FR1"))
                .thenReturn(Optional.of(account));

        assertThrows(
                OverdraftExceededException.class,
                () -> service.withdraw("FR1",
                        "client1",
                        2000));

        verify(repository, never()).save(any());
    }
}
