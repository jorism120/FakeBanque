package fr.mns.accountservice.domain.model;


public class CurrentAccount extends WithdrawalAccount {
    private double overdraft;

    public CurrentAccount(String iban, String clientId, double initialBalance, double overdraft) {
        super(iban, clientId, initialBalance);
        this.overdraft = overdraft;
    }

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }
}
