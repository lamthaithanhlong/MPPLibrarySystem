package org.miu.mpp.ui.login;

import org.miu.mpp.ui.LibrarySystem;
import org.miu.mpp.ui.base.Dialog;
import org.miu.mpp.ui.base.JFrameAddMultiple;
import org.miu.mpp.ui.ruleset.RuleException;
import org.miu.mpp.ui.ruleset.RuleSetFactory;
import org.miu.mpp.utils.SystemController;
import org.miu.mpp.utils.Util;

import javax.swing.*;
import java.util.List;

public class LoginWindow extends JFrameAddMultiple {
    public static LoginWindow loginWindowInstance = new LoginWindow();
    private JTextField idField;
    private JTextField passwordField;

    public String getIdValue() {
        return idField.getText();
    }

    public String getPasswordValue() {
        return passwordField.getText();
    }

    private SystemController systemController;

    //private constructor to keep initialization in the class
    private LoginWindow() {
    }

    public void init() {
        initData();
        this.systemController = new SystemController();
    }

    private void initData() {
        JLabel textField = new JLabel("Welcome to STARBOYs LMS");
        textField.setBounds(120, 10, 200, 20);

        JLabel idLabel = new JLabel("id");
        idLabel.setBounds(100, 30, 200, 40);
        idField = new JTextField();
        idField.setBounds(100, 70, 200, 40);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(100, 110, 200, 40);
        passwordField = new JPasswordField();
        passwordField.setBounds(100, 150, 200, 40);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 210, 100, 30);

        loginButton.addActionListener(e -> {
            checkRules();
        });

        addAll(List.of(textField, idLabel, idField, passwordLabel, passwordField, loginButton));

        setLayout(null);
        setSize(400, 300);
    }

    private void checkRules() {
        try {
            RuleSetFactory.getRuleSet(this).applyRules(this);

            try {
                systemController.login(idField.getText().trim(), passwordField.getText());

                LibrarySystem.hideAllWindows();
                LibrarySystem.librarySystemInstance.initAndShow();
                Util.centerFrameOnDesktop(LibrarySystem.librarySystemInstance);
            } catch (LoginException exception) {
                new Dialog("Error", exception.getMessage(), true);
            }
        } catch (RuleException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Group13:Error", 1);
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        LoginWindow.loginWindowInstance.init();
    }

    public void clearData() {
        idField.setText("");
        passwordField.setText("");
    }
}
