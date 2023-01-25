package org.miu.mpp.utils;

import org.miu.mpp.ui.login.LoginException;

import java.util.List;

public interface ControllerInterface {
    public void login(String id, String password) throws LoginException;
    public List<String> getAllMemberIds();
    public List<String> getAllBooksId();
}
