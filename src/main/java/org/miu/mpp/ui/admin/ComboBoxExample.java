package org.miu.mpp.ui.admin;

import javax.swing.*;

public class ComboBoxExample {
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("ComboBox Example");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JComboBox
        String[] memberRoles = {"admin", "user", "guest"};
        JComboBox roles = new JComboBox(memberRoles);

        // Add the JComboBox to the JFrame
        frame.add(roles);

        // Display the JFrame
        frame.setVisible(true);
    }
}
