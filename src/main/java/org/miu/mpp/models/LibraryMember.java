package org.miu.mpp.models;


import java.io.Serializable;

public class LibraryMember extends Person implements Serializable {
    private String memberId;

    public LibraryMember(String memberId, String firstName, String lastName, String phone, Address address) {
        super(firstName, lastName, phone, address);
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
