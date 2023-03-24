package org.miu.mpp.ui.login;

import org.miu.mpp.ui.LibrarySystem;
import org.miu.mpp.ui.base.Dialog;
import org.miu.mpp.ui.base.JFrameAddMultiple;
import org.miu.mpp.ui.ruleset.RuleException;
import org.miu.mpp.ui.ruleset.RuleSetFactory;
import org.miu.mpp.utils.AppStringConstants;
import org.miu.mpp.utils.SystemController;
import org.miu.mpp.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.List;

public class LoginWindow extends JFrameAddMultiple {
    public static LoginWindow loginWindowInstance = new LoginWindow();

    final Logger logger = LoggerFactory.getLogger(LoginWindow.class);

    private JTextField idField;
    private JTextField passwordField;

    public String getIdValue() {
        return idField.getText();
    }

    public String getPasswordValue() {
        return passwordField.getText();
    }

    private final SystemController systemController;

    //private constructor to keep initialization in the class
    private LoginWindow() {
        this.systemController = new SystemController();
    }

    public void init() {
        JLabel welcomeLabel = new JLabel(AppStringConstants.WELCOME_TEXT.getValue());
        welcomeLabel.setBounds(120, 10, 200, 20);

        JLabel idLabel = new JLabel(AppStringConstants.ID.getValue());
        idLabel.setBounds(100, 30, 200, 40);
        idField = new JTextField();
        idField.setBounds(100, 70, 200, 40);

        JLabel passwordLabel = new JLabel(AppStringConstants.PASSWORD.getValue());
        passwordLabel.setBounds(100, 110, 200, 40);
        passwordField = new JPasswordField();
        passwordField.setBounds(100, 150, 200, 40);

        JButton loginButton = new JButton(AppStringConstants.LOGIN.getValue());
        loginButton.setBounds(150, 210, 100, 30);

        loginButton.addActionListener(e -> logUserIn());

        this.addAll(List.of(welcomeLabel, idLabel, idField, passwordLabel, passwordField, loginButton));

        setLayout(null);
        setSize(400, 300);
    }

    private void logUserIn() {
        try {
            applyLoginRule();
            attemptLogin();
        } catch (RuleException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "LoginWindow:logUserIn()", 1);
            logger.info("{}", e.getMessage());
        }
    }

    private void applyLoginRule() throws RuleException {
        RuleSetFactory.getRuleSet(this).applyRules(this);
    }

    private void attemptLogin() {
        try {
            systemController.login(idField.getText().trim(), passwordField.getText());

            LibrarySystem.hideAllWindows();
            LibrarySystem.librarySystemInstance.initAndShow();
            Util.centerFrameOnDesktop(LibrarySystem.librarySystemInstance);
        } catch (LoginException exception) {
            new Dialog(AppStringConstants.ERROR.getValue(), exception.getMessage(), true);
        }
    }

    public static void main(String[] args) {
        LoginWindow.loginWindowInstance.init();
        LoginWindow.loginWindowInstance.setVisible(true);
    }

    public void clearData() {
        idField.setText("");
        passwordField.setText("");
    }
}
