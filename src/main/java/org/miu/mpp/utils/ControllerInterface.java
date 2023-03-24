package org.miu.mpp.utils;

import org.miu.mpp.ui.login.LoginException;

import java.util.List;

public interface ControllerInterface {
    void login(String id, String password) throws LoginException;

    List<String> getAllMemberIds();

    List<String> getAllBooksId();
}
