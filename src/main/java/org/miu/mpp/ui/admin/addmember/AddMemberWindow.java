package org.miu.mpp.ui.admin.addmember;

import org.miu.mpp.ui.base.Dialog;
import org.miu.mpp.ui.base.JFrameAddMultiple;
import org.miu.mpp.ui.base.UIHelper;
import org.miu.mpp.ui.ruleset.RuleException;
import org.miu.mpp.ui.ruleset.RuleSetFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author bazz
 * Date Jan 26 2023
 * Time 15:05
 */
public class AddMemberWindow extends JFrameAddMultiple implements UIHelper {

    public static AddMemberWindow addMemberWindowInstance = new AddMemberWindow();

    private boolean isInitialized = false;

    private JLabel firstNameLabel, bioLabel, roleSepLabel, lastNameLabel, phoneLabel, addressLabel, stateLabel, cityLabel, streetLabel, zipLabel, rolesLabel, passwordLabel;
    private JTextField firstNameField, lastNameField, phoneField, stateField, cityField, streetField, zipField, passwordField;

    private JComboBox role;
    private String selectedRole;
    private JButton addMemberBtn;

    String[] memberRoles = {"LIBRARY MEMBER", "LIBERIAN", "ADMIN", "ADMIN AND LIBERIAN"};


    public String getFirstNameFieldValue() {
        return firstNameField.getText();
    }

    public String getLastNameFieldValue() {
        return lastNameField.getText();
    }

    public String getPhoneFieldValue() {
        return phoneField.getText();
    }

    public String getStateFieldValue() {
        return stateField.getText();
    }

    public String getCityFieldValue() {
        return cityField.getText();
    }

    public String getStreetFieldValue() {
        return streetField.getText();
    }

    public String getZipFieldValue() {
        return zipField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public String getSelectedRole() {
        return selectedRole;
    }

    private void setSelectedRole(String role) {
        System.out.println("selected role : " + role);
        this.selectedRole = role;
    }

    private AddMemberWindow() {
    }

    public void initData() {
        setTitle("Add Member");


        JLabel titleLabel = new JLabel("Please Fill in the details below to add a new Member. ");
        titleLabel.setBounds(126, 33, 600, 60);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.ITALIC, titleLabel.getFont().getSize()));
        titleLabel.setForeground(Color.BLUE);

        callGoBack(20, 10, 100, 30);

//1002 28-12331
        bioLabel = new JLabel("Personal Information: ");
        bioLabel.setFont(new Font(bioLabel.getFont().getName(), Font.ITALIC, titleLabel.getFont().getSize()));
        bioLabel.setBounds(20, 70, 350, 30);

        JSeparator bioSeperator = new JSeparator(SwingConstants.HORIZONTAL);
        bioSeperator.setPreferredSize(new Dimension(200, 2));
        bioSeperator.setForeground(Color.DARK_GRAY);
        bioSeperator.setBounds(19, 95, 500, 10);


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
        addrSeperator.setBounds(19, 205, 500, 10);

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
        roleSeperator.setBounds(19, 360, 500, 10);


        rolesLabel = new JLabel("Select Member Role: ");
        rolesLabel.setBounds(23, 380, 150, 30);

        role = new JComboBox(memberRoles);
        role.setBounds(19, 400, 150, 30);
        role.setVisible(true);

        role.setSelectedIndex(0);
        this.selectedRole = memberRoles[0];


        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(190, 380, 100, 20);

        passwordField = new JPasswordField();
        passwordField.setBounds(190, 400, 150, 30);
        addAll(Arrays.asList(passwordField, passwordLabel));
        passwordField.setVisible(false);
        passwordLabel.setVisible(false);


        role.addItemListener(e -> {
            setSelectedRole((String) e.getItem());
            if (e.getItem().equals("LIBRARY MEMBER")) {
                passwordField.setVisible(false);
                passwordLabel.setVisible(false);
            } else {
                passwordField.setVisible(true);
                passwordLabel.setVisible(true);
            }
        });


        addMemberBtn = new JButton("Add Member");
        addMemberBtn.setBounds(360, 400, 150, 30);

        addMemberBtn.addActionListener(e -> {
            try {
                RuleSetFactory.getRuleSet(addMemberWindowInstance).applyRules(addMemberWindowInstance);
                try {
                    MemberDetailsPojo memberDetailsPojo = new MemberDetailsPojo(addMemberWindowInstance);
                    System.out.println("memberDetailsPojo.getMemberRole() " + memberDetailsPojo.getMemberRole());
                    String memberId = AddMemberController.createNewMemberFromUi(memberDetailsPojo);
                    new Dialog("Success", "New Member/User added Successfully with ID:  " + memberId, false);

                    clearData();
                } catch (AddMemberException addMemberException) {
                    new Dialog("Error", addMemberException.getMessage(), true);
                }

            } catch (RuleException ruleException) {
                new Dialog("Error", ruleException.getMessage(), true);
            }
        });


        setSize(600, 600);
        setLayout(null);

        addAll(Arrays.asList(titleLabel, bioSeperator, addrSeperator, bioLabel, firstNameLabel, firstNameField, lastNameLabel, lastNameField,
                phoneLabel, phoneField, addressLabel, stateLabel, stateField, cityLabel, cityField, streetLabel, streetField,
                zipLabel, zipField, rolesLabel, role, roleSepLabel, roleSeperator, addMemberBtn
        ));
    }

    private void clearData() {
        clearAllTfs(List.of(firstNameField, lastNameField, phoneField, stateField, cityField, streetField, zipField));
    }

    private void clearAllTfs(List<JTextField> allTfs) {
        for (JTextField tf : allTfs) {
            tf.setText("");
        }
    }


    public static void main(String[] args) {
        addMemberWindowInstance.initData();
    }

    @Override
    public void init() {

    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {
        isInitialized = val;
    }


    class MemberDetailsPojo {
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String state;
        private String city;
        private String street;
        private String zip;
        private String memberRole;
        private String password;

        private MemberDetailsPojo(String firstName, String lastName, String phoneNumber, String state,
                                  String city, String street, String zip, String memberRole, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.state = state;
            this.city = city;
            this.street = street;
            this.zip = zip;
            this.password = password;
            this.memberRole = memberRole;
        }


        public MemberDetailsPojo(AddMemberWindow addMemberWindowInstance) {
            System.out.println("addMemberWindowInstance.getFirstNameFieldValue() :" + addMemberWindowInstance.getFirstNameFieldValue());
            this.firstName = addMemberWindowInstance.getFirstNameFieldValue();
            this.lastName = addMemberWindowInstance.getLastNameFieldValue();
            this.phoneNumber = addMemberWindowInstance.getPhoneFieldValue();
            this.state = addMemberWindowInstance.getStateFieldValue();
            this.city = addMemberWindowInstance.getCityFieldValue();
            this.street = addMemberWindowInstance.getStreetFieldValue();
            this.zip = addMemberWindowInstance.getZipFieldValue();
            this.password = addMemberWindowInstance.getPassword();
            this.memberRole = addMemberWindowInstance.getSelectedRole();
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getState() {
            return state;
        }

        public String getCity() {
            return city;
        }

        public String getStreet() {
            return street;
        }

        public String getZip() {
            return zip;
        }

        public String getMemberRole() {
            return memberRole;
        }

        public String getPassword() {
            return password;
        }
    }

}
