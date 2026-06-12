package com.example.bank.model.person;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public abstract class Person {
    public int id;
    public String name;
    public String firstname;
    public String address;

    public Person(String address, String firstname, String name, int id) {
        this.address = address;
        this.firstname = firstname;
        this.name = name;
        this.id = id;
    }
}
