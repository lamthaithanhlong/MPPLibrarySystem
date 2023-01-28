package org.miu.mpp.ui.returnbook;

import org.miu.mpp.dataaccess.DataAccess;
import org.miu.mpp.dataaccess.DataAccessFacade;
import org.miu.mpp.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReturnBookWindowController {
    private final DataAccess da;

    public ReturnBookWindowController() {
        this.da = new DataAccessFacade();
    }

    public List<CheckoutRecord> getAllCheckoutRecords() {
        List<CheckoutRecord> records = new ArrayList<>();
        List<LibraryMember> members = new ArrayList<>();

        da.readMemberMap().forEach((key, value) -> {
            members.add(value);
        });

        for (LibraryMember member : members) {
            if (member.getCheckoutRecord() != null)
                records.add(member.getCheckoutRecord());
        }

        return records;
    }

    public CheckOutHistoryDto getCheckoutRecordBasedOnIsbn(String isbn, String memberId) throws ReturnBookException {
        LibraryMember member = da.readMemberMap().get(memberId);

        if (member == null) throw new ReturnBookException("Member not found with id " + memberId);
        CheckoutRecord checkoutRecord = member.getCheckoutRecord();

        CheckoutEntry matchingCheckoutEntry = null;
        CheckoutRecord matchingCheckoutRecord = null;

        for (CheckoutEntry checkoutEntry : checkoutRecord.getEntries()) {
            if (checkoutEntry.getBookCopy().getBook().getIsbn().equals(isbn) && !checkoutEntry.isReturned()) {
                matchingCheckoutEntry = checkoutEntry;
                matchingCheckoutRecord = checkoutRecord;
                break;
            }
        }

        if (matchingCheckoutEntry != null) {
            return new CheckOutHistoryDto(matchingCheckoutEntry.getBookCopy(),
                    matchingCheckoutEntry.getCheckoutDate(),
                    matchingCheckoutEntry.getDueDate(),
                    matchingCheckoutRecord.getMemberId(),
                    matchingCheckoutEntry.getDueFee());
        } else return null;
    }

    public void markBookAsReturnedAndAvailable(CheckOutHistoryDto checkOutHistoryDto) {
        LibraryMember member = da.readMemberMap().get(checkOutHistoryDto.getMemberId());
        CheckoutRecord checkoutRecord = member.getCheckoutRecord();

        BookCopy bookCopy;

        if (checkoutRecord != null) {
            for (CheckoutEntry entry : checkoutRecord.getEntries()) {
                if (entry.getBookCopy().getBook().getTitle().equals(checkOutHistoryDto.getBookCopy().getBook().getTitle())
                        && entry.getBookCopy().getCopyNum() == checkOutHistoryDto.getBookCopy().getCopyNum()) {
                    bookCopy = entry.getBookCopy();

                    entry.setIsReturned();
                    da.saveNewMember(member);
                    updateBookCopyAvailability(bookCopy, bookCopy.getBook().getIsbn());
                    break;
                }
            }
        }
    }

    private void updateBookCopyAvailability(BookCopy bc, String isbn) {
        HashMap<String, Book> bookMap = da.readBooksMap();

        for (BookCopy bookCopy : bookMap.get(isbn).getCopies()) {
            if (bc.getCopyNum() == bookCopy.getCopyNum()) {
                bookCopy.changeAvailability();
                break;
            }
        }

        da.saveNewBook(bookMap.get(isbn));
    }
}
