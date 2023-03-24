package org.miu.mpp.ui.admin.addmember;

import org.miu.mpp.dataaccess.DataAccess;
import org.miu.mpp.dataaccess.DataAccessFacade;
import org.miu.mpp.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author bazz
 * Date Jan 26 2023
 * Time 18:10
 */
public class AddMemberController {

    private static final DataAccess da = new DataAccessFacade();


    public static String getNextMemberId() {

        DataAccess da = new DataAccessFacade();
        Set<String> mems = da.readMemberMap().keySet();
        Integer lastValue = Integer.parseInt(mems.iterator().next());
        for (String i : mems) {
            lastValue = Integer.parseInt(i);
        }
        System.out.println("last value is: " + lastValue);
        return String.valueOf(getNextId(mems, lastValue));
    }

    private static Integer getNextId(Set<String> mems, Integer lastValue) {
        Integer nextMemberId = lastValue + 1;

        while (mems.contains(String.valueOf(nextMemberId))) {
            nextMemberId = nextMemberId + 1;
        }

        return nextMemberId;
    }


    public static String createNewMemberFromUi(AddMemberWindow.MemberDetailsPojo memberDetailsPojo) throws AddMemberException {


        String id = null;
        if (memberDetailsPojo.getMemberRole().equalsIgnoreCase("LIBRARY MEMBER")) {

            List<LibraryMember> libraryMembers = new ArrayList<>(da.readMemberMap().values());
            for (LibraryMember v : libraryMembers) {
                if (v.getPhone().equals(memberDetailsPojo.getPhoneNumber())) {
                    throw new AddMemberException(String.format("A Library member with phone %s already exists", memberDetailsPojo.getPhoneNumber()));
                }
            }
            id = getNextMemberId();
            LibraryMember newLibraryMember = new LibraryMember(id, memberDetailsPojo.getFirstName(), memberDetailsPojo.getLastName(),
                    memberDetailsPojo.getPhoneNumber(), new Address(memberDetailsPojo.getStreet(),
                    memberDetailsPojo.getCity(), memberDetailsPojo.getState(), memberDetailsPojo.getZip()));
            da.saveNewMember(newLibraryMember);
        } else {
            Auth auth = getAuth(memberDetailsPojo.getMemberRole());
            if (auth == null) {
                throw new AddMemberException("Invalid Member Type Entered!");
            }
            List<User> users = new ArrayList<>(da.readUserMap().values());

            for (User v : users) {
                if (v.getPerson() != null && v.getPerson().getPhone().equals(memberDetailsPojo.getPhoneNumber())) {
                    throw new AddMemberException(String.format("A User with phone %s already exists", memberDetailsPojo.getPhoneNumber()));
                }
            }
            id = getNextUserId();
            User user = User.createFromPerson(id, memberDetailsPojo.getPassword(), auth,
                    new Person(memberDetailsPojo.getFirstName(), memberDetailsPojo.getPassword(), memberDetailsPojo.getPhoneNumber(),
                            new Address(memberDetailsPojo.getStreet(), memberDetailsPojo.getCity(), memberDetailsPojo.getState(), memberDetailsPojo.getZip())));

            da.saveNewUser(user);
        }
        return id;
    }

    private static Auth getAuth(String memberRole) {
        switch (memberRole) {
            case "LIBERIAN":
                return Auth.LIBRARIAN;
            case "ADMIN":
                return Auth.ADMIN;
            case "ADMIN AND LIBERIAN":
                return Auth.BOTH;
            default:
                return null;
        }
    }


    public static String getNextUserId() {

        Set<String> users = da.readUserMap().keySet();
        Integer lastValue = Integer.parseInt(users.iterator().next());
        for (String i : users) {
            lastValue = Integer.parseInt(i);
        }
        return String.valueOf(getNextId(users, lastValue));
    }


}
