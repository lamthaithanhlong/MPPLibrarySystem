package org.miu.mpp.ui.base;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JFrameAddMultiple extends JFrame {

    public void addMultipleComponents(List<? extends Component> allComponents) {
        for (Component singleComponent : allComponents) {
            this.add(singleComponent);
        }
    }
}
