package com.exerice.fakebanque.model.person;

import lombok.Getter;
import lombok.Setter;

public abstract class Person {
    @Getter
    @Setter
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
