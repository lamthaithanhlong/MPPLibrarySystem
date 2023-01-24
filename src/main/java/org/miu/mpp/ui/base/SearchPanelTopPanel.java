package org.miu.mpp.ui.base;

import javax.swing.*;
import java.util.List;

public class SearchPanelTopPanel extends JFrameAddMultiple {
    private final String searchText;
    private final String buttonText;

    public SearchPanelTopPanel(String searchText, String buttonText) {
        this.searchText = searchText;
        this.buttonText = buttonText;

        setPanelDetails();
    }

    private void setPanelDetails() {
        JTextField jTextField = new JTextField();
        jTextField.setToolTipText(searchText);
        jTextField.setBounds(20, 20, 300, 40);

        JButton jButton = new JButton(buttonText);
        jButton.setBounds(340, 25, 100, 30);

        this.addMultipleComponents(List.of(jTextField, jButton));

        this.setLayout(null);
        this.setVisible(true);
        this.setSize(500, 500);
    }

    public static void main(String[] args) {
        new SearchPanelTopPanel("isbn", "Search");
    }
}
