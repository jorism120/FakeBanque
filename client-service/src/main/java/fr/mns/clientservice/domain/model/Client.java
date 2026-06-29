package fr.mns.clientservice.domain.model;

import lombok.Data;

@Data
public class Client {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public Client(String id, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}
