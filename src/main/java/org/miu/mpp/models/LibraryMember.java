package org.miu.mpp.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

final public class LibraryMember extends Person implements Serializable {
    @Getter
    private String memberId;

    @Getter
    @Setter
    private CheckoutRecord checkoutRecord;

    public LibraryMember(String memberId, String fname, String lname, String tel, Address add) {
        super(fname, lname, tel, add);
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() +
                ", " + getPhone() + " " + getAddress();
    }

    private static final long serialVersionUID = -2226197306790714013L;
}
