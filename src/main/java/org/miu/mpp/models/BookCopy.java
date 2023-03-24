package org.miu.mpp.models;

import lombok.Getter;

import java.io.Serializable;

/**
 * Immutable class
 */
@Getter
final public class BookCopy implements Serializable {

    private static final long serialVersionUID = -63976228084869815L;

    private Book book;

    private int copyNum;

    private boolean isAvailable;

    BookCopy(Book book, int copyNum, boolean isAvailable) {
        this.book = book;
        this.copyNum = copyNum;
        this.isAvailable = isAvailable;
    }

    public void changeAvailability() {
        isAvailable = !isAvailable;
    }

    @Override
    public boolean equals(Object ob) {
        if (ob == null) return false;
        if (!(ob instanceof BookCopy)) return false;
        BookCopy copy = (BookCopy) ob;
        return copy.book.getIsbn().equals(book.getIsbn()) && copy.copyNum == copyNum;
    }
}
