package org.miu.mpp.ui.admin.addbook;

import javax.swing.*;
import java.awt.*;

public class Main 
{

    public static void main(String[] args) 
    {
        new Main();
    }

    Main()
    {
        JFrame frame = new JFrame("MyFrame");
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        JLabel left = new JLabel("LEFT");
        JLabel right = new JLabel("RIGHT");
        JPanel top = new JPanel(new BorderLayout());

        top.add(left, BorderLayout.WEST);
        top.add(right, BorderLayout.EAST);
        panel.add(top, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JLabel("Another dummy Label"), BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}