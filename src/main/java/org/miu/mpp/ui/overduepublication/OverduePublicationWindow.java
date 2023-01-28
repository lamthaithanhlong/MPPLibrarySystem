package org.miu.mpp.ui.overduepublication;

import org.miu.mpp.models.CheckoutRecord;
import org.miu.mpp.ui.LibrarySystem;
import org.miu.mpp.ui.base.JFrameAddMultiple;
import org.miu.mpp.ui.checkoutbook.CheckOutHistoryPojo;
import org.miu.mpp.ui.checkoutbook.CheckoutBookController;
import org.miu.mpp.ui.checkoutbook.CheckoutHistoryWindow;

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
 * Date Jan 27 2023
 * Time 13:43
 */
public class OverduePublicationWindow extends JFrameAddMultiple {
    public static OverduePublicationWindow overduePublicationWindowInstace = new OverduePublicationWindow();


    private CheckoutBookController checkoutBookController;

    private JLabel isbnLabel;
    private JTextField isbnField;
    private JTable table;

    private JScrollPane scrollPane;

    private DefaultTableModel model;
    List<CheckOutHistoryPojo> allCheckoutRecordsPojo;


    public String getIsbnFieldText() {
        return isbnField.getText().trim();
    }


    private void setIsbnFieldText(String text) {
        isbnField.setText(text);
    }


    private OverduePublicationWindow() {
    }


    public void initData() {
        this.checkoutBookController = new CheckoutBookController();

        allCheckoutRecordsPojo = getCheckoutPojo(checkoutBookController.getAllCheckoutRecords());


        JButton goBackBtn = new JButton("<< Go Back");
        goBackBtn.setBounds(20, 10, 100, 30);
        goBackBtn.addActionListener(v -> {
            LibrarySystem.hideAllWindows();
            LibrarySystem.librarySystemInstance.initAndShow();
        });

        JLabel titleLabel = new JLabel("You can filter by entering ISBN below or just click on search to view all records");
        titleLabel.setBounds(26, 33, 600, 60);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.ITALIC, titleLabel.getFont().getSize()));
        titleLabel.setForeground(Color.BLUE);

        setTitle("Overdue Publications");

        isbnLabel = new JLabel("ISBN: ");
        isbnLabel.setBounds(25, 80, 150, 30);

        isbnField = new JTextField();
        isbnField.setBounds(20, 105, 180, 30);

        JButton jButton = new JButton("Search");
        jButton.setBounds(400, 105, 100, 30);

        jButton.addActionListener(v -> {
            addSearchFilter();
        });


        getTable();


        addAll(Arrays.asList(isbnLabel, isbnField, jButton, scrollPane, goBackBtn, titleLabel));

        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private List<CheckOutHistoryPojo> getCheckoutPojo(List<CheckoutRecord> allCheckoutRecords) {
        List<CheckOutHistoryPojo> checkOutHistoryPojos = new ArrayList<>();

        allCheckoutRecords.forEach(v -> v.getEntries().forEach(y -> checkOutHistoryPojos
                .add(new CheckOutHistoryPojo(y.getBookCopy(), y.getCheckoutDate(),y.getReturnDate(), y.getDueDate(), v.getMemberId(), y.getDueFee()))));


        return checkOutHistoryPojos.stream().filter(v -> v.getDueDate().isBefore(LocalDate.now())).collect(Collectors.toList());
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
        table.setFocusable(false);
        table.setEnabled(false);


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
        if (getIsbnFieldText().isBlank()) {
            allCheckoutRecordsPojo = getCheckoutPojo(checkoutBookController.getAllCheckoutRecords());
        }

        getTable();
    }


    public static void main(String[] args) {
        OverduePublicationWindow.overduePublicationWindowInstace.initData();
        OverduePublicationWindow.overduePublicationWindowInstace.setVisible(true);
    }
}
