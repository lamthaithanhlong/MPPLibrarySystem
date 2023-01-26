package org.miu.mpp.ui.checkoutbook;

import org.miu.mpp.models.BookCopy;
import org.miu.mpp.models.CheckoutRecord;
import org.miu.mpp.ui.LibrarySystem;
import org.miu.mpp.ui.base.JFrameAddMultiple;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author bazz
 * Date Jan 25 2023
 * Time 20:29
 */
public class CheckoutHistoryWindow extends JFrameAddMultiple {
    public static CheckoutHistoryWindow checkoutHistoryWindowInstance = new CheckoutHistoryWindow();

    private CheckoutBookController checkoutBookController;

    private JLabel memberIdLabel, isbnLabel;
    private JTextField memberField, isbnField;
    private JTable table;

    private JScrollPane scrollPane;

    private DefaultTableModel model;
    List<CheckoutRecord> allCheckoutRecords;
    List<CheckOutHistoryPojo> allCheckoutRecordsPojo;


    public String getMemberIdFieldText() {
        return memberField.getText().trim();
    }

    public String getIsbnFieldText() {
        return isbnField.getText().trim();
    }


    private CheckoutHistoryWindow() {}

    private void setIsbnFieldText(String text) {
        isbnField.setText(text);
    }

    private void setMemberIdField(String text) {
        memberField.setText(text);
    }

    public static void loadCheckoutHistoryWindowWithFilter(String isbn, String memberId) {
        CheckoutHistoryWindow checkoutHistoryWindow = new CheckoutHistoryWindow();
        checkoutHistoryWindow.setIsbnFieldText(isbn);
        checkoutHistoryWindow.setMemberIdField(memberId);
        checkoutHistoryWindow.addSearchFilter();

    }

    public static void loadCheckoutHistoryFromDashboard() {
        new CheckoutHistoryWindow();
    }


    public void initData() {
        this.checkoutBookController = new CheckoutBookController();

        allCheckoutRecords = checkoutBookController.getAllCheckoutRecords();
        allCheckoutRecordsPojo = getCheckoutPojo(allCheckoutRecords);


        JButton goBackBtn = new JButton("<< Go Back");
        goBackBtn.setBounds(20, 10, 100, 30);
        goBackBtn.addActionListener(v -> LibrarySystem.librarySystemInstance.initAndShow());

        JLabel titleLabel = new JLabel("You can filter by entering an of the fields below or just click on search to view all records");
        titleLabel.setBounds(26, 33, 600, 60);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.ITALIC, titleLabel.getFont().getSize()));
        titleLabel.setForeground(Color.BLUE);

        setTitle("Book Checkout History");


        memberIdLabel = new JLabel("Member ID: ");
        memberIdLabel.setBounds(25, 80, 150, 30);

        memberField = new JTextField();
        memberField.setBounds(20, 105, 180, 30);


        isbnLabel = new JLabel("ISBN: ");
        isbnLabel.setBounds(205, 80, 150, 30);

        isbnField = new JTextField();
        isbnField.setBounds(200, 105, 180, 30);

        JButton jButton = new JButton("Search");
        jButton.setBounds(400, 105, 100, 30);

        jButton.addActionListener(v -> {
            addSearchFilter();
        });


        getTable();


        addAll(Arrays.asList(memberField, memberIdLabel, isbnLabel, isbnField, jButton, scrollPane, goBackBtn, titleLabel));

        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private List<CheckOutHistoryPojo> getCheckoutPojo(List<CheckoutRecord> allCheckoutRecords) {
        List<CheckOutHistoryPojo> checkOutHistoryPojos = new ArrayList<>();

        allCheckoutRecords.forEach(v -> v.getEntries().forEach(y -> checkOutHistoryPojos
                .add(new CheckOutHistoryPojo(y.getBookCopy(), y.getCheckoutDate(), y.getDueDate(), v.getMemberId(), y.getDueFee()))));

        return checkOutHistoryPojos;
    }

    private void getTable() {
        if (Objects.nonNull(model)) {
            model.setRowCount(0);
            table.updateUI();
            scrollPane.remove(table);
            remove(scrollPane);
        }
        scrollPane = new JScrollPane();
        scrollPane.setBounds(6, 154, 582, 287);

        table = new JTable();

        model = new DefaultTableModel();
        String[] column = {"Member Id", "Book Title", "ISBN", "Copy No.", "Checkout Date", "Due Date", "Late Fee"};
        model.setColumnIdentifiers(column);
        table.setModel(model);


        for (CheckOutHistoryPojo cr : allCheckoutRecordsPojo) {
//            for (CheckoutEntry ce : cr.getEntries()) {
            model.insertRow(0, new String[]{
                    cr.getMemberId(),
                    cr.getBookCopy().getBook().getTitle(),
                    cr.getBookCopy().getBook().getIsbn(),
                    String.valueOf(cr.getBookCopy().getCopyNum()),
                    cr.getCheckoutDate().toString(),
                    cr.getDueDate().toString(),
                    "$" + cr.getAmountDue()
            });
        }
        scrollPane.setViewportView(table);
        add(scrollPane);
    }

    private void addSearchFilter() {

        if (!getIsbnFieldText().isBlank()) {
            allCheckoutRecordsPojo = allCheckoutRecordsPojo.stream().filter(v -> v.getBookCopy().getBook().getIsbn().equalsIgnoreCase(getIsbnFieldText())).collect(Collectors.toList());
        }
        if (!getMemberIdFieldText().isBlank()) {
            allCheckoutRecordsPojo = allCheckoutRecordsPojo.stream().filter(v -> v.getMemberId().equalsIgnoreCase(getMemberIdFieldText())).collect(Collectors.toList());
        }
        if (getMemberIdFieldText().isBlank() && getIsbnFieldText().isBlank()) {
            allCheckoutRecordsPojo = getCheckoutPojo(allCheckoutRecords);
        }

        getTable();
    }

    public static void main(String[] args) {
        new CheckoutHistoryWindow();
    }


    class CheckOutHistoryPojo {
        private BookCopy bookCopy;
        private LocalDate checkoutDate;
        private LocalDate dueDate;
        private String memberId;
        private double amountDue;

        public CheckOutHistoryPojo(BookCopy bookCopy, LocalDate checkoutDate, LocalDate dueDate, String memberId, double dueFee) {
            this.bookCopy = bookCopy;
            this.checkoutDate = checkoutDate;
            this.dueDate = dueDate;
            this.memberId = memberId;
            this.amountDue = dueFee;
        }

        public BookCopy getBookCopy() {
            return bookCopy;
        }

        public LocalDate getCheckoutDate() {
            return checkoutDate;
        }

        public LocalDate getDueDate() {
            return dueDate;
        }

        public String getMemberId() {
            return memberId;
        }

        public double getAmountDue() {
            return amountDue;
        }
    }

}
