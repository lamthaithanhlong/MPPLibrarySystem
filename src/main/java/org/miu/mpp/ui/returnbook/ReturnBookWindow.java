package org.miu.mpp.ui.returnbook;

import org.miu.mpp.ui.LibrarySystem;
import org.miu.mpp.ui.base.Dialog;
import org.miu.mpp.ui.base.SearchPanelTopPanel;
import org.miu.mpp.ui.ruleset.RuleException;
import org.miu.mpp.ui.ruleset.RuleSetFactory;
import org.miu.mpp.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class ReturnBookWindow extends SearchPanelTopPanel {

    private String userSearchText;

    JLabel bookTitleInfo;

    JLabel bookCheckoutInfo;

    JLabel bookDueDateInfo;

    JLabel bookDaysLateInfo;
    CheckOutHistoryDto foundCheckoutEntry;

    private ReturnBookWindowController returnBookWindowController = new ReturnBookWindowController();

    public String getUserSearchText() {
        return userSearchText;
    }

    public static ReturnBookWindow returnBookWindowInstance = new ReturnBookWindow();

    private ReturnBookWindow() {
    }

    public void init() {
        setTitle("Return Book");
        setupSearchPanelDetails();

        setLayout(null);
        setSize(new Dimension(600, 500));
    }

    private void setupVisuals(CheckOutHistoryDto foundCheckoutEntry) {
        if (foundCheckoutEntry.getAmountDue() > 0) {
            JLabel overDueLabel = new JLabel("Overdue Fee :");
            overDueLabel.setBounds(20, 100, 600, 30);
            overDueLabel.setFont(new Font(overDueLabel.getFont().getName(), Font.BOLD, 20));
            overDueLabel.setForeground(Color.RED);

            JLabel bookDaysLate = new JLabel("Book days late by: ");
            bookDaysLate.setBounds(20, 245, 200, 30);
            bookDaysLate.setForeground(Color.RED);

            int daysDue = Period.between(foundCheckoutEntry.getDueDate(), LocalDate.now()).getDays();
            bookDaysLateInfo = new JLabel(String.valueOf(daysDue));
            bookDaysLateInfo.setBounds(230, 245, 350, 30);
            bookDaysLateInfo.setForeground(Color.RED);

            addAll(List.of(overDueLabel, bookDaysLate, bookDaysLateInfo));
        }

        JLabel bookTitleLabel = new JLabel("Book title: ");
        bookTitleLabel.setBounds(20, 140, 200, 30);

        bookTitleInfo = new JLabel(foundCheckoutEntry.getBookCopy().getBook().getTitle());
        bookTitleInfo.setBounds(230, 140, 350, 30);

        JLabel bookCheckoutDate = new JLabel("Book checkout date: ");
        bookCheckoutDate.setBounds(20, 175, 200, 30);

        bookCheckoutInfo = new JLabel(foundCheckoutEntry.getCheckoutDate().toString());
        bookCheckoutInfo.setBounds(230, 175, 350, 30);

        JLabel bookDueDate = new JLabel("Book due date: ");
        bookDueDate.setBounds(20, 210, 200, 30);

        bookDueDateInfo = new JLabel(foundCheckoutEntry.getDueDate().toString());
        bookDueDateInfo.setBounds(230, 210, 350, 30);

        JButton returnBtn = new JButton("Return");
        returnBtn.setBounds(340, 300, 100, 30);
        returnBtn.addActionListener(e -> setCopyAsReturned());

        addAll(List.of(bookTitleLabel, bookTitleInfo,
                bookCheckoutDate, bookCheckoutInfo, bookDueDate,
                bookDueDateInfo, returnBtn));
    }

    //    99-22223
    private void setCopyAsReturned() {
        try {
            returnBookWindowController.markBookAsReturnedAndAvailable(foundCheckoutEntry);

            new Dialog("Success", "Book returned Successfully", false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setupSearchPanelDetails() {
        setSearchText("isbn");
        setButtonText("search");
        initPanel();
    }

    @Override
    public void clickListenerForSearchBtn(String searchText, ActionEvent e) {
        try {
            //set user search text here so that it can be accessed in the AddBookWindowRuleSet Class
            userSearchText = searchText;
            RuleSetFactory.getRuleSet(this).applyRules(this);

            foundCheckoutEntry = returnBookWindowController.getCheckoutRecordBasedOnIsbn(userSearchText);

            if (foundCheckoutEntry != null) {
                setupVisuals(foundCheckoutEntry);
                revalidate();
                repaint();
            } else new Dialog("Error", "No Checkout entry found for Book with isbn - " + searchText, false);

        } catch (RuleException ruleException) {
            new Dialog("Error", ruleException.getMessage(), true);
            System.out.println(ruleException.getMessage());
        }
    }

    @Override
    public void clickListenerForBackBtn(ActionEvent e) {
        LibrarySystem.hideAllWindows();

        LibrarySystem.librarySystemInstance.initAndShow();
        Util.centerFrameOnDesktop(LibrarySystem.librarySystemInstance);
    }

    public static void main(String[] args) {
        ReturnBookWindow.returnBookWindowInstance.init();
        ReturnBookWindow.returnBookWindowInstance.setVisible(true);
    }
}
