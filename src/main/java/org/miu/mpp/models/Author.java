package org.miu.mpp.models;

import java.io.Serializable;

public class Author extends Person implements Serializable {
    private String bio;

    public Author(String firstName, String lastName, String phone, Address address, String bio) {
        super(firstName, lastName, phone, address);
        this.bio = bio;
    }
}
