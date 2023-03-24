package org.miu.mpp.models;

import lombok.Getter;
import org.miu.mpp.utils.AppConstants;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 */
@Getter
final public class Book implements Serializable {

    private BookCopy[] copies;
    private List<Author> authors;
    private String isbn;
    private String title;
    private int maxCheckoutLength;

    public Book(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
        this.isbn = isbn;
        this.title = title;
        this.maxCheckoutLength = maxCheckoutLength;
        this.authors = Collections.unmodifiableList(authors);
        copies = new BookCopy[]{new BookCopy(this, AppConstants.NEW_B00K_DEFAULT_COPY.getOneBookCopy(), true)};
    }

    public void addNewCopy() {
        BookCopy[] copiesHolder = new BookCopy[copies.length + 1];
        System.arraycopy(copies, 0, copiesHolder, 0, copies.length);
        copiesHolder[copies.length] = new BookCopy(this, copies.length + 1, true);
        copies = copiesHolder;
    }

    @Override
    public boolean equals(Object ob) {
        if (ob == null) return false;
        if (ob.getClass() != getClass()) return false;
        Book b = (Book) ob;
        return b.isbn.equals(isbn);
    }

    public boolean isAvailable() {
        if (copies == null) {
            return false;
        }
        return Arrays.stream(copies)
                .map(BookCopy::isAvailable)
                .reduce(false, (x, y) -> x || y);
    }

    @Override
    public String toString() {
        return "isbn: " + isbn + ", title: " + title + ", maxLength: " + maxCheckoutLength + ", available: " + isAvailable();
    }

    public int getNumCopies() {
        return copies.length;
    }
}
