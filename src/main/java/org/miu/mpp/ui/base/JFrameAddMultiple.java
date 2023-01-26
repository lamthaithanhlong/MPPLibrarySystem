package org.miu.mpp.ui.base;

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
}
