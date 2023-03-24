package org.miu.mpp.models;

import lombok.Getter;
import org.miu.mpp.utils.AppConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
public final class CheckoutRecord implements Serializable {

    private static final long serialVersionUID = 7274497633871237909L;

    private List<CheckoutEntry> entries;
    private String memberId;

    private CheckoutRecord(String memberId) {
        this.memberId = memberId;
        this.entries = new ArrayList<>();
    }

    public static CheckoutRecord createCheckoutRecord(LibraryMember member) {
        if (null == member)
            throw new IllegalStateException(AppConstants.MEMBER_CANNOT_BE_NULL.getValue());

        CheckoutRecord checkoutRecord = new CheckoutRecord(member.getMemberId());

        member.setCheckoutRecord(checkoutRecord);

        return checkoutRecord;
    }

    public void addCheckoutEntry(CheckoutEntry entry) {
        this.entries.add(entry);
    }
}
