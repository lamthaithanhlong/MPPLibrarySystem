package org.miu.mpp.ui.base;

import org.miu.mpp.ui.LibrarySystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JFrameAddMultiple extends JFrame {

    public JFrameAddMultiple() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
//        setLocationRelativeTo(null);
    }

    public void addAll(List<? extends Component> allComponents) {
        for (Component singleComponent : allComponents) {
            this.add(singleComponent);
        }
    }

    public void callGoBack(int x, int y, int width, int height) {

        JButton goBackBtn = new JButton("<< Go Back");
        goBackBtn.setBounds(x, y, width, height);

        goBackBtn.addActionListener(v -> {
            LibrarySystem.hideAllWindows();
            LibrarySystem.librarySystemInstance.initAndShow();
        });
        add(goBackBtn);
    }
}
