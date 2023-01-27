package org.miu.mpp.ui.admin.addbookcopy;

import org.miu.mpp.dataaccess.DataAccess;
import org.miu.mpp.dataaccess.DataAccessFacade;
import org.miu.mpp.models.Book;

import java.util.ArrayList;
import java.util.List;

public class AddBookCopyController {
    public List<Book> getAllBooks() {
        DataAccess da = new DataAccessFacade();
        List<Book> records = new ArrayList<>();

        da.readBooksMap().forEach((key, value) -> {
            records.add(value);
        });

        return records;
    }

    public Book getBook(String isbn) {
        DataAccess da = new DataAccessFacade();
        return da.readBooksMap().get(isbn);
    }

    public void saveBook(Book book) {
        DataAccess da = new DataAccessFacade();
        da.saveNewBook(book);
    }
}
