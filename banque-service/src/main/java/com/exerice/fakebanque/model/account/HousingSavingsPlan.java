package com.exerice.fakebanque.model.account;

public class HousingSavingsPlan extends Account {
    private boolean status;

    public HousingSavingsPlan(String iban, double initialBalance, boolean status) {
        super(iban, initialBalance);
        this.status = status;
    }
}
