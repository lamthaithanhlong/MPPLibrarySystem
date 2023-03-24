package org.miu.mpp.dataaccess;

import org.miu.mpp.models.Book;
import org.miu.mpp.models.LibraryMember;
import org.miu.mpp.models.User;

import java.util.HashMap;

public interface DataAccess {
    HashMap<String, Book> readBooksMap();

    HashMap<String, User> readUserMap();

    HashMap<String, LibraryMember> readMemberMap();

    void saveNewMember(LibraryMember member);

    void saveNewUser(User user);

    void saveNewBook(Book book);
}
