package org.miu.mpp.models;

import lombok.Getter;

import java.io.Serializable;

@Getter
public final class User implements Serializable {

    private static final long serialVersionUID = 5147265048973262104L;

    private String id;

    private String password;

    private Auth authorization;

    private Person person;

    private User(String id, String pass, Auth auth) {
        this.id = id;
        this.password = pass;
        this.authorization = auth;
    }

    private User(String id, String pass, Auth auth, Person person) {
        this.person = person;
        this.id = id;
        this.password = pass;
        this.authorization = auth;
    }

    public static User create(String id, String pass, Auth auth) {
        return new User(id, pass, auth);
    }

    public static User createFromPerson(String id, String pass, Auth auth, Person person) {
        return new User(id, pass, auth, person);
    }

    @Override
    public String toString() {
        return "[" + id + ":" + password + ", " + authorization.toString() + "]";
    }
}
