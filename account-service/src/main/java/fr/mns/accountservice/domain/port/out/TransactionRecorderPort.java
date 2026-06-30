package fr.mns.accountservice.domain.port.out;

public interface TransactionRecorderPort {
    void record(String iban, String type, double amount);
}
