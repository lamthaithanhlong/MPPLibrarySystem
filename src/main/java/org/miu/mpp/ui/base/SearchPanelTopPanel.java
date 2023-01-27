package org.miu.mpp.ui.base;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public abstract class SearchPanelTopPanel extends JFrameAddMultiple {
    private String searchText;
    private String buttonText;

    public abstract void clickListenerForSearchBtn(String searchText, ActionEvent e);

    public abstract void clickListenerForBackBtn(ActionEvent e);

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public void initPanel() {
        JButton backBtn = new JButton("<< Go Back");
        backBtn.setBounds(20, 20, 100, 30);
        backBtn.addActionListener(this::clickListenerForBackBtn);

        JTextField jTextField = new JTextField();
        jTextField.setToolTipText(searchText);
        jTextField.setBounds(20, 60, 300, 30);

        JButton jButton = new JButton(buttonText);
        jButton.setBounds(340, 60, 100, 30);
        jButton.addActionListener(e -> clickListenerForSearchBtn(jTextField.getText(), e));

        this.addAll(List.of(backBtn, jTextField, jButton));
    }
}
