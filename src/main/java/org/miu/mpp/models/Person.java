package org.miu.mpp.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Person implements Serializable {
    private String firstName;
    private String lastName;
    private String phone;

    private Address address;

    public Person(String firstName, String lastName, String phone, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }
}
