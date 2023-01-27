package org.miu.mpp.ui.returnbook;

import org.miu.mpp.models.BookCopy;

import java.time.LocalDate;

public class CheckOutHistoryDto {
    private BookCopy bookCopy;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private String memberId;
    private double amountDue;

    public CheckOutHistoryDto(BookCopy bookCopy, LocalDate checkoutDate, LocalDate dueDate, String memberId, double dueFee) {
        this.bookCopy = bookCopy;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.memberId = memberId;
        this.amountDue = dueFee;
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
}