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

    public void login(String id, String password) throws LoginException {
        DataAccess da = new DataAccessFacade();
        HashMap<String, User> map = da.readUserMap();
        if (!map.containsKey(id)) {
            throw new LoginException("User with ID " + id + " not found");
        }
        String passwordFound = map.get(id).getPassword();
        if (!passwordFound.equals(password)) {
            throw new LoginException("Password incorrect");
        }
        loggedInUser = map.get(id);
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
