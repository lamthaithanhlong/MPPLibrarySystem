package org.miu.mpp.dataaccess;

import org.miu.mpp.models.Book;
import org.miu.mpp.models.LibraryMember;
import org.miu.mpp.models.User;

import java.util.HashMap;

public interface DataAccess {
    public HashMap<String, Book> readBooksMap();

    public HashMap<String, User> readUserMap();

    public HashMap<String, LibraryMember> readMemberMap();

    public void saveNewMember(LibraryMember member);
}
