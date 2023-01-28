package org.miu.mpp.ui.admin.addbook;

import org.miu.mpp.dataaccess.DataAccess;
import org.miu.mpp.dataaccess.DataAccessFacade;
import org.miu.mpp.models.Book;

import java.util.ArrayList;
import java.util.List;

public class AddBookController {
    private DataAccess da;

    public AddBookController() {
        da = new DataAccessFacade();
    }

    public Book getBook(String isbn) {
        da = new DataAccessFacade();
        return da.readBooksMap().get(isbn);
    }

    public List<Book> getAllBooks() {
        da = new DataAccessFacade();
        List<Book> books = new ArrayList<Book>();
        da.readBooksMap().forEach((key, value) -> {
            books.add(value);
        });
        return books;
    }

    public void addBook(Book book) {
        da = new DataAccessFacade();
        da.saveNewBook(book);
    }

    public List<String> allBookIds() {
        da = new DataAccessFacade();
        List<String> retval = new ArrayList<>();
        retval.addAll(da.readBooksMap().keySet());
        return retval;
    }
}