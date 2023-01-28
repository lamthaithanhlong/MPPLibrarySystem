package org.miu.mpp.ui.checkoutbook;

import org.miu.mpp.models.BookCopy;

import java.time.LocalDate;

public class CheckOutHistoryPojo {
    private BookCopy bookCopy;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private String memberId;
    private LocalDate returnDate;
    private double amountDue;

    public CheckOutHistoryPojo(BookCopy bookCopy, LocalDate checkoutDate, LocalDate returnDate, LocalDate dueDate, String memberId, double dueFee) {
        this.bookCopy = bookCopy;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.memberId = memberId;
        this.amountDue = dueFee;
        this.returnDate = returnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getMemberId() {
        return memberId;
    }

    public double getAmountDue() {
        return amountDue;
    }

    @Override
    public String toString() {
        return String.format("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |", bookCopy.getCopyNum(), checkoutDate, dueDate, memberId, returnDate, amountDue);
    }
}
