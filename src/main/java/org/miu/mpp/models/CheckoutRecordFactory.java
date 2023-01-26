package org.miu.mpp.models;

import java.time.LocalDate;

public class CheckoutRecordFactory {
	public static CheckoutRecord createCheckoutRecord(LibraryMember member) {
		if(null == member)
			throw new IllegalStateException("Library member cannot be null");
		
		CheckoutRecord checkoutRecord = new CheckoutRecord(member.getMemberId());
		
		member.setCheckoutRecord(checkoutRecord);
		
		return checkoutRecord;
	}
	
	public static CheckoutEntry createCheckoutEntry(BookCopy bookCopy, LocalDate date) {
		return new CheckoutEntry(bookCopy, date);
	}
}
