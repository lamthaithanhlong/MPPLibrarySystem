package org.miu.mpp.models;

import org.miu.mpp.utils.AppConstants;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

final public class CheckoutEntry implements Serializable {

    private static final long serialVersionUID = -6050615539255649506L;
    private BookCopy bookCopy;
    private LocalDate checkoutDate;
    private LocalDate dueDate;

    private LocalDate returnDate;
    private boolean isReturned;

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setIsReturned() {
        isReturned = true;
    }

    CheckoutEntry(BookCopy bookCopy, LocalDate checkoutDate) {
        this.bookCopy = bookCopy;
        this.checkoutDate = checkoutDate;
        this.dueDate = checkoutDate.plusDays(bookCopy.getBook().getMaxCheckoutLength());
        this.isReturned = false;
        this.returnDate = null;
    }

    public double getDueFee() {
        int days = Period.between(dueDate, LocalDate.now()).getDays();
        if (days > 0) {
            return days * AppConstants.LATE_FEE;
        }
        return 0.0;
    }

}
