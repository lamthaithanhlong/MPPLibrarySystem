package org.miu.mpp.ui.admin.addmember;

import org.miu.mpp.dataaccess.DataAccess;
import org.miu.mpp.dataaccess.DataAccessFacade;
import org.miu.mpp.models.LibraryMember;
import org.miu.mpp.models.User;
import org.miu.mpp.ui.ruleset.RuleException;
import org.miu.mpp.ui.ruleset.RuleSet;
import org.miu.mpp.ui.ruleset.RuleSetFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bazz
 * Date Jan 26 2023
 * Time 18:36
 */
public class AddMemberRuleSet implements RuleSet {


    @Override
    public void applyRules(Component ob) throws RuleException {

        AddMemberWindow addMemberWindow = (AddMemberWindow) ob;

        String firstName = addMemberWindow.getFirstNameFieldValue();
        String lastName = addMemberWindow.getLastNameFieldValue();
        String phone = addMemberWindow.getPhoneFieldValue();
        String state = addMemberWindow.getStateFieldValue();
        String city = addMemberWindow.getCityFieldValue();
        String street = addMemberWindow.getStreetFieldValue();
        String zip = addMemberWindow.getZipFieldValue();
        String password = addMemberWindow.getPassword();
        String role = addMemberWindow.getSelectedRole();


        if (RuleSetFactory.isAnyEmpty(List.of(firstName, lastName, phone, state, city, street, zip))) {

            System.out.println("firstName "+firstName);
            System.out.println("lastName "+lastName);
            System.out.println("phone "+phone);
            System.out.println("state "+state);
            System.out.println("city "+city);
            System.out.println("street "+street);
            System.out.println("zip "+zip);
            throw new RuleException("Please ensure no fields are empty");
        }
        if (!role.equalsIgnoreCase("LIBRARY_MEMBER")) {
            if (RuleSetFactory.isAnyEmpty(List.of(password))) {
                throw new RuleException("Password is required for user role: " + role);
            }
        }

        if (!RuleSetFactory.isValidZipCode(zip)) {
            throw new RuleException("Please enter only valid US Zip Code");
        }
        if (!RuleSetFactory.isValidPhoneNumber(phone)) {
            throw new RuleException("Please enter only valid US phone number");
        }

        DataAccess da = new DataAccessFacade();

        List<LibraryMember> libraryMembers = new ArrayList<>(da.readMemberMap().values());
        List<User> users = new ArrayList<>(da.readUserMap().values());


    }
}
