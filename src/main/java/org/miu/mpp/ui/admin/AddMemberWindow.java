package org.miu.mpp.ui.admin;

import org.miu.mpp.models.Auth;
import org.miu.mpp.ui.base.JFrameAddMultiple;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * @author bazz
 * Date Jan 26 2023
 * Time 15:05
 */
public class AddMemberWindow extends JFrameAddMultiple {

    public static AddMemberWindow addMemberWindowInstance = new AddMemberWindow();


    private JLabel firstNameLabel,bioLabel,roleSepLabel, lastNameLabel, phoneLabel, addressLabel, stateLabel, cityLabel, streetLabel, zipLabel,rolesLabel;
    private JTextField firstNameField, lastNameField, phoneField, stateField, cityField, streetField, zipField;

    private JComboBox roles;
    private JButton addMemberBtn;

    String[] memberRoles = {"ADMIN", "LIBERIAN", "ADMIN_AND_LIBERIAN", "LIBRARY_MEMBER"};

    private AddMemberWindow() {
        initData();
    }

    private void initData() {
        setTitle("Add Member");


        JLabel titleLabel = new JLabel("Please Fill in the details below to add a new Member. ");
        titleLabel.setBounds(126, 33, 600, 60);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.ITALIC, titleLabel.getFont().getSize()));
        titleLabel.setForeground(Color.BLUE);

        callGoBack(20, 10, 100, 30);


        bioLabel = new JLabel("Personal Information: ");
        bioLabel.setFont(new Font(bioLabel.getFont().getName(), Font.ITALIC, titleLabel.getFont().getSize()));
        bioLabel.setBounds(20, 70, 350, 30);

        JSeparator bioSeperator = new JSeparator(SwingConstants.HORIZONTAL);
        bioSeperator.setPreferredSize(new Dimension(200, 2));
        bioSeperator.setForeground(Color.DARK_GRAY);
        bioSeperator.setBounds(19,95,500,10);


        firstNameLabel = new JLabel("Firstname: ");
        firstNameLabel.setBounds(23, 120, 100, 20);

        firstNameField = new JTextField();
        firstNameField.setBounds(20, 140, 150, 30);


        lastNameLabel = new JLabel("Lastname: ");
        lastNameLabel.setBounds(190, 120, 100, 20);

        lastNameField = new JTextField();
        lastNameField.setBounds(190, 140, 150, 30);

        phoneLabel = new JLabel("Phone number: ");
        phoneLabel.setBounds(360, 120, 100, 20);

        phoneField = new JTextField();
        phoneField.setBounds(360, 140, 150, 30);



        JSeparator addrSeperator = new JSeparator(SwingConstants.HORIZONTAL);
        addrSeperator.setPreferredSize(new Dimension(200, 2));
        addrSeperator.setForeground(Color.DARK_GRAY);
        addrSeperator.setBounds(19,205,500,10);

        addressLabel = new JLabel("Address Information: ");
        addressLabel.setFont(new Font(addressLabel.getFont().getName(), Font.ITALIC, titleLabel.getFont().getSize()));
        addressLabel.setBounds(20, 190, 300, 20);





        stateLabel = new JLabel("State: ");
        stateLabel.setBounds(23, 220, 100, 20);

        stateField = new JTextField();
        stateField.setBounds(20, 240, 150, 30);


        cityLabel = new JLabel("City: ");
        cityLabel.setBounds(190, 220, 100, 20);

        cityField = new JTextField();
        cityField.setBounds(190, 240, 150, 30);

        streetLabel = new JLabel("Street: ");
        streetLabel.setBounds(360, 220, 100, 20);

        streetField = new JTextField();
        streetField.setBounds(360, 240, 150, 30);

        zipLabel = new JLabel("Zip: ");
        zipLabel.setBounds(23, 280, 100, 20);

        zipField = new JTextField();
        zipField.setBounds(20, 300, 150, 30);


        roleSepLabel = new JLabel("Roles: ");
        roleSepLabel.setFont(new Font(roleSepLabel.getFont().getName(), Font.ITALIC, titleLabel.getFont().getSize()));
        roleSepLabel.setBounds(20, 340, 300, 20);

        JSeparator roleSeperator = new JSeparator(SwingConstants.HORIZONTAL);
        roleSeperator.setPreferredSize(new Dimension(200, 2));
        roleSeperator.setForeground(Color.DARK_GRAY);
        roleSeperator.setBounds(19,360,500,10);



        rolesLabel = new JLabel("Select Member Role: ");
        rolesLabel.setBounds(23, 380, 150, 30);

        roles = new JComboBox(memberRoles);
        roles.setBounds(19, 400, 150, 30);
        roles.setVisible(true);


        addMemberBtn = new JButton("Add Member");
        addMemberBtn.setBounds(360, 400, 150, 30);



        setSize(600, 600);
        setLayout(null);
        setVisible(true);

        addAll(Arrays.asList(titleLabel,bioSeperator,addrSeperator,bioLabel,firstNameLabel,firstNameField, lastNameLabel,lastNameField,
                phoneLabel,phoneField, addressLabel, stateLabel,stateField, cityLabel,cityField, streetLabel, streetField,
                zipLabel,zipField,rolesLabel,roles,roleSepLabel,roleSeperator,addMemberBtn
                   ));

    }


    public static void main(String[] args) {
        addMemberWindowInstance.initData();
    }
}
