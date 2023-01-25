package org.miu.mpp.ui.base;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public abstract class SearchPanelTopPanel extends JFrameAddMultiple {
    private String searchText;
    private String buttonText;

    public abstract void clickListenerForSearchBtn(ActionEvent e);

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public void initPanelAndShow() {
        JTextField jTextField = new JTextField();
        jTextField.setToolTipText(searchText);
        jTextField.setBounds(20, 20, 300, 40);

        JButton jButton = new JButton(buttonText);
        jButton.setBounds(340, 25, 100, 30);
        jButton.addActionListener(this::clickListenerForSearchBtn);

        this.addMultipleComponents(List.of(jTextField, jButton));
    }
}
