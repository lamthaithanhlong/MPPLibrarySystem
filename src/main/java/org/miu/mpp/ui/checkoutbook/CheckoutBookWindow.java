package org.miu.mpp.ui.checkoutbook;

import org.miu.mpp.ui.LibrarySystem;
import org.miu.mpp.ui.base.Dialog;
import org.miu.mpp.ui.base.JFrameAddMultiple;
import org.miu.mpp.ui.ruleset.RuleException;
import org.miu.mpp.ui.ruleset.RuleSetFactory;
import org.miu.mpp.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * @author bazz
 * Date Jan 25 2023
 * Time 14:45
 */
public class CheckoutBookWindow extends JFrameAddMultiple {

    public static CheckoutBookWindow checkoutBookWindowInstance = new CheckoutBookWindow();

    private CheckoutBookController checkoutBookController;

    private JLabel memberIdLabel, isbnLabel;
    private JTextField memberField, isbnField;

    private CheckoutBookWindow() {
    }

    public String getMemberIdFieldText() {
        return memberField.getText().trim();
    }

    public String getIsbnFieldText() {
        return isbnField.getText().trim();
    }

    public static void displayCheckOutBookWindow() {
        new CheckoutBookWindow();
    }

    public void initData() {
        this.checkoutBookController = new CheckoutBookController();

        setTitle("Checkout Book");

        JLabel titleLabel = new JLabel("Enter Member ID & ISBN to Checkout a book ");
        titleLabel.setBounds(126, 33, 600, 60);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.ITALIC, titleLabel.getFont().getSize()));
        titleLabel.setForeground(Color.BLUE);

        JButton goBackBtn = new JButton("<< Go Back");
        goBackBtn.setBounds(20, 10, 100, 30);

        goBackBtn.addActionListener(v -> {
            LibrarySystem.hideAllWindows();
            LibrarySystem.librarySystemInstance.initAndShow();
        });

        memberIdLabel = new JLabel("Member ID: ");
        memberIdLabel.setBounds(20, 70, 200, 30);

        memberField = new JTextField();
        memberField.setBounds(20, 95, 300, 30);


        isbnLabel = new JLabel("ISBN: ");
        isbnLabel.setBounds(20, 130, 200, 30);

        isbnField = new JTextField();
        isbnField.setBounds(20, 150, 300, 30);


        JButton jButton = new JButton("Checkout Book");
        jButton.setBounds(350, 150, 140, 30);
        jButton.addActionListener(v -> {
            checkRules();
        });

        addAll(Arrays.asList(memberField, memberIdLabel, isbnLabel, isbnField, jButton, goBackBtn, titleLabel));


        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private void checkRules() {
        try {
            RuleSetFactory.getRuleSet(this).applyRules(this);
            try {
                checkoutBookController.checkoutBook(memberField.getText().trim(), isbnField.getText().trim(), LocalDate.now());
                new Dialog("Success", "Book checked out to member: " + memberField.getText() + " Successfully", false);

                LibrarySystem.hideAllWindows();
                CheckoutHistoryWindow.checkoutHistoryWindowInstance.initData();
                CheckoutHistoryWindow.loadCheckoutHistoryWindowWithFilter("", memberField.getText().trim());
                Util.centerFrameOnDesktop(CheckoutHistoryWindow.checkoutHistoryWindowInstance);
                CheckoutHistoryWindow.checkoutHistoryWindowInstance.setVisible(true);

            } catch (CheckoutBookException exception) {
                new Dialog("Error", exception.getMessage(), true);
            }
        } catch (RuleException e) {
            new Dialog("Error", e.getMessage(), true);
            System.out.println(e.getMessage());
        }

    }


    public static void main(String[] args) {
        CheckoutBookWindow.checkoutBookWindowInstance.initData();
        CheckoutBookWindow.checkoutBookWindowInstance.setVisible(true);
    }

}
