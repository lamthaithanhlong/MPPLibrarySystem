package org.miu.mpp.models;

import lombok.Getter;
import org.miu.mpp.utils.AppConstants;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Getter
public final class CheckoutEntry implements Serializable {

    private static final long serialVersionUID = -6050615539255649506L;
    private BookCopy bookCopy;
    private LocalDate checkoutDate;
    private LocalDate dueDate;

    private LocalDate returnDate;

    private boolean isReturned;

    public void setIsReturned() {
        isReturned = true;
    }

    public void setReturnDate() {
        returnDate = LocalDate.now();
    }

    private CheckoutEntry(BookCopy bookCopy, LocalDate checkoutDate) {
        this.bookCopy = bookCopy;
        this.checkoutDate = checkoutDate;
        this.dueDate = checkoutDate.plusDays(bookCopy.getBook().getMaxCheckoutLength());
        this.isReturned = false;
        this.returnDate = null;
    }

    public static CheckoutEntry createCheckoutEntry(BookCopy bookCopy, LocalDate date) {
        return new CheckoutEntry(bookCopy, date);
    }

    public double getDueFee() {
        int days = Period.between(dueDate, LocalDate.now()).getDays();
        if (days > 0 && !isReturned) {
            return days * AppConstants.LATE_FEE.getLateFee();
        }
        return 0.0;
    }
}
