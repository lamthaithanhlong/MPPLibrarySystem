package org.miu.mpp.ui;

import org.miu.mpp.ui.base.SearchPanelTopPanel;

import java.awt.event.ActionEvent;

public class TestSearchTopPanel extends SearchPanelTopPanel {

    @Override
    public void clickListenerForSearchBtn(String searchText, ActionEvent e) {
        System.out.println("Something has been clicked");
    }

    @Override
    public void clickListenerForBackBtn(ActionEvent e) {

    }

    public static void main(String[] args) {
        TestSearchTopPanel testSearchTopPanel = new TestSearchTopPanel();
        testSearchTopPanel.setSearchText("isbn");
        testSearchTopPanel.setButtonText("search");
        testSearchTopPanel.initPanel();

        testSearchTopPanel.setLayout(null);
        testSearchTopPanel.setVisible(true);
        testSearchTopPanel.setSize(500, 500);
    }
}
