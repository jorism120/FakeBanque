package fr.mns.accountservice.domain.model;

public class HousingSavingsPlan extends Account {
    private boolean status;

    public HousingSavingsPlan(String iban, String clientId, double initialBalance, boolean status) {
        super(iban, clientId, initialBalance);
        this.status = status;
    }
}
