package org.miu.mpp.models;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {
    private String isbn;
    private String name;
    private int maxBorrowDays;

    private List<Author> authors;

    public Book(String isbn, String name, int maxBorrowDays, List<Author> authors) {
        this.isbn = isbn;
        this.name = name;
        this.maxBorrowDays = maxBorrowDays;
        this.authors = authors;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxBorrowDays() {
        return maxBorrowDays;
    }

    public void setMaxBorrowDays(int maxBorrowDays) {
        this.maxBorrowDays = maxBorrowDays;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addCopy() {

    }
}
