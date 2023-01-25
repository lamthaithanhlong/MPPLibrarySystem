package org.miu.mpp.ui;

import org.miu.mpp.ui.base.SearchPanelTopPanel;

import java.awt.event.ActionEvent;

public class TestSearchTopPanel extends SearchPanelTopPanel {

    @Override
    public void clickListenerForSearchBtn(ActionEvent e) {
        System.out.println("Something has been clicked");
    }

    public static void main(String[] args) {
        TestSearchTopPanel testSearchTopPanel = new TestSearchTopPanel();
        testSearchTopPanel.setSearchText("isbn");
        testSearchTopPanel.setButtonText("search");
        testSearchTopPanel.initPanelAndShow();

        testSearchTopPanel.setLayout(null);
        testSearchTopPanel.setVisible(true);
        testSearchTopPanel.setSize(500, 500);
    }
}
