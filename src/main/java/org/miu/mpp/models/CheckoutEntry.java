package org.miu.mpp.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

final public class CheckoutEntry implements Serializable {

    private static final long serialVersionUID = -6050615539255649506L;
    private BookCopy bookCopy;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private static final double LATE_FEE = 0.25;

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    CheckoutEntry(BookCopy bookCopy, LocalDate checkoutDate) {
        this.bookCopy = bookCopy;
        this.checkoutDate = checkoutDate;
        this.dueDate = checkoutDate.plusDays(bookCopy.getBook().getMaxCheckoutLength());
    }

    public double getDueFee() {
        int days = Period.between(dueDate, LocalDate.now()).getDays();
        if (days > 0) {
            return days * LATE_FEE;
        }
        return 0.0;
    }

}
