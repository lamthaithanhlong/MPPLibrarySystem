package org.miu.mpp;

import org.miu.mpp.ui.login.LoginWindow;
import org.miu.mpp.utils.Util;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (Exception e) {
                }
                LoginWindow.loginWindowInstance.init();
                Util.centerFrameOnDesktop(LoginWindow.loginWindowInstance);
                LoginWindow.loginWindowInstance.setVisible(true);
            }
        });
    }
}