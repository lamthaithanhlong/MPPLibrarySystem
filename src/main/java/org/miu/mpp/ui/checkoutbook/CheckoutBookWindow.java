package org.miu.mpp.ui.checkoutbook;

import org.miu.mpp.ui.base.Dialog;
import org.miu.mpp.ui.base.JFrameAddMultiple;
import org.miu.mpp.ui.base.SearchPanelTopPanel;
import org.miu.mpp.ui.ruleset.RuleException;
import org.miu.mpp.ui.ruleset.RuleSetFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * @author bazz
 * Date Jan 25 2023
 * Time 14:45
 */
public class CheckoutBookWindow extends JFrameAddMultiple {

    private final CheckoutBookController checkoutBookController;

    private JLabel memberIdLabel, isbnLabel;
    private JTextField memberField, isbnField;

    private CheckoutBookWindow() {
        initData();
        this.checkoutBookController = new CheckoutBookController();
    }

    public String getMemberIdFieldText(){
        return memberField.getText().trim();
    }
    public String getIsbnFieldText(){
        return isbnField.getText().trim();
    }

    public void initData() {

        memberIdLabel = new JLabel("Member ID: ");
        memberIdLabel.setBounds(20, 20, 200, 30);

        memberField = new JTextField();
        memberField.setBounds(20, 50, 300, 40);


        isbnLabel = new JLabel("ISBN: ");
        isbnLabel.setBounds(20, 90, 200, 30);

        isbnField = new JTextField();
        isbnField.setBounds(20, 120, 300, 40);


        JButton jButton = new JButton("Checkout Book");
        jButton.setBounds(350, 120, 140, 40);
        jButton.addActionListener(v -> {
            checkRules();
        });

        addAll(Arrays.asList(memberField, memberIdLabel, isbnLabel, isbnField, jButton));


        setSize(600, 500);
        setLayout(null);
        setVisible(true);
    }


    private void checkRules() {
        try {
            RuleSetFactory.getRuleSet(this).applyRules(this);
            try {
                checkoutBookController.checkoutBook(memberField.getText().trim(), isbnField.getText().trim(), LocalDate.now());
                new Dialog("Success", "Book checked out to member: " + memberField.getText() + " Successfully", false);

            } catch (CheckoutBookException exception) {
                new Dialog("Error", exception.getMessage(), true);
            }
        } catch (RuleException e) {
            new Dialog("Error", e.getMessage(), true);
            System.out.println(e.getMessage());
        }

    }



    public static void main(String[] args) {
        new CheckoutBookWindow();
    }

}
