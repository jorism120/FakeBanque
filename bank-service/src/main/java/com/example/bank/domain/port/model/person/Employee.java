package com.example.bank.model.person;

public class Employee extends Person
{
    private String password;

    public Employee(String address, String firstname, String name, int id, String password) {
        super(address, firstname, name, id);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
