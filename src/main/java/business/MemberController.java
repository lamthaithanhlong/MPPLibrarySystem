package business;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import org.miu.mpp.models.Address;
import org.miu.mpp.models.LibraryMember;
import org.miu.mpp.utils.SystemController;

public class MemberController {
	public boolean memberExists(String memberId, List<String> memberIds) {
		return memberIds.contains(memberId);
	}
	public List<LibraryMember> getAllMembers() {
		org.miu.mpp.dataaccess.DataAccessFacade da = new org.miu.mpp.dataaccess.DataAccessFacade();
		ArrayList<LibraryMember> libraryMembers = new ArrayList<LibraryMember>();
		da.readMemberMap().forEach((key, value) -> {
			libraryMembers.add(value);
		});
		return libraryMembers;
	}
	public LibraryMember getMember(String memberId) {
		org.miu.mpp.dataaccess.DataAccessFacade da = new org.miu.mpp.dataaccess.DataAccessFacade();
		HashMap<String, LibraryMember> members = da.readMemberMap();
		LibraryMember libraryMember = members.get(memberId);
		return libraryMember;
	}
	public void updateMember(LibraryMember libraryMember) {
		org.miu.mpp.dataaccess.DataAccess da = new org.miu.mpp.dataaccess.DataAccessFacade();
		da.saveNewMember(libraryMember);
	}
	public void deleteMember(String memberId) {
		org.miu.mpp.dataaccess.DataAccessFacade da = new org.miu.mpp.dataaccess.DataAccessFacade();
		da.deleteMember(memberId);
	}
	public void addMember(String memberId, String fname, String lname,
			String tel, String street, String city, String state, String zip)
			throws LibrarySystemException {
		Address address = new Address(street, city, state, zip);
		LibraryMember libraryMember = new LibraryMember(memberId, fname, lname,
				tel, address);
		org.miu.mpp.dataaccess.DataAccessFacade dataAccess = new org.miu.mpp.dataaccess.DataAccessFacade();
		dataAccess.saveNewMember(libraryMember);
	}

	public void getAndUpdateMember(String memberId, String fname, String lname,
			String tel) throws LibrarySystemException {
		SystemController sc = new SystemController();
		if (!memberExists(memberId, sc.getAllMemberIds())) {
			throw new LibrarySystemException("Member not found.");
		}

		LibraryMember libraryMember = getMember(memberId);
		libraryMember.setFirstName(fname);
		libraryMember.setLastName(lname);
		libraryMember.setPhone(tel);

		updateMember(libraryMember);
	}
}