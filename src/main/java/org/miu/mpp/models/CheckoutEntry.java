package org.miu.mpp.models;

import java.io.Serializable;
import java.time.LocalDate;

final public class CheckoutEntry implements Serializable{

	private static final long serialVersionUID = -6050615539255649506L;
	private BookCopy bookCopy;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	
	public BookCopy getBookCopy() {
		return bookCopy;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}
	
	CheckoutEntry(BookCopy bookCopy, LocalDate checkoutDate){
		this.bookCopy = bookCopy;
		this.checkoutDate = checkoutDate;
		this.dueDate = checkoutDate.plusDays(bookCopy.getBook().getMaxCheckoutLength());
	}

}
