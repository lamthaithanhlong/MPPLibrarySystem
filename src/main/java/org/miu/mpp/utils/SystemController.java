package org.miu.mpp.utils;

import org.miu.mpp.dataaccess.DataAccess;
import org.miu.mpp.dataaccess.DataAccessFacade;
import org.miu.mpp.models.User;
import org.miu.mpp.ui.login.LoginException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SystemController implements ControllerInterface {
    public static User loggedInUser = null;
    private static HashMap<String, User> userRecords;

    public SystemController() {
        DataAccess da = new DataAccessFacade();
        userRecords = da.readUserMap();
    }

    public void login(String id, String password) throws LoginException {
        attemptLogin(id, password);
        setLoggedInUser(id);
    }

    private void attemptLogin(String id, String password) throws LoginException {
        if (!userRecords.containsKey(id)) {
            throw new LoginException("User with ID " + id + " not found");
        }
        String userPassword = userRecords.get(id).getPassword();
        if (!userPassword.equals(password)) {
            throw new LoginException(AppStringConstants.PASSWORD_INCORRECT.getValue());
        }
    }

    private static void setLoggedInUser(String userId) {
        loggedInUser = userRecords.get(userId);
    }

    @Override
    public List<String> getAllMemberIds() {
        DataAccess da = new DataAccessFacade();
        List<String> retval = new ArrayList<>();
        retval.addAll(da.readMemberMap().keySet());
        return retval;
    }

    @Override
    public List<String> getAllBooksId() {
        DataAccess da = new DataAccessFacade();
        List<String> retval = new ArrayList<>();
        retval.addAll(da.readBooksMap().keySet());
        return retval;
    }
}
