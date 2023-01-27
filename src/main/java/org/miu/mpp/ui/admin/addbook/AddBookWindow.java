package org.miu.mpp.ui.admin.addbook;

import org.miu.mpp.ui.LibrarySystem;
import org.miu.mpp.ui.base.JFrameAddMultiple;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddBookWindow extends JFrameAddMultiple {
    public static AddBookWindow addBookWindowInstance = new AddBookWindow();

    String[] checkoutBookLength = {"7", "21"};

    private JPanel mainPanel;

    private JPanel authorsContainerPanel;
    private JPanel topPanel;
    private JPanel isbnAndTitleLabelPanel;
    private JPanel isbnAndTitleTfPanel;

    private JScrollPane scrollPane;

    private JTextField isbnTf;
    private JTextField titleTf;
    private JTextField copiesTf;

    private JButton saveBtn;

    private int numOfAuthor = 0;

    private List<JTextField> authorsFirstName = new ArrayList<JTextField>();
    private List<JTextField> authorsLastName = new ArrayList<JTextField>();
    private List<JTextField> authorsTelephone = new ArrayList<JTextField>();
    private List<JTextField> authorsStreet = new ArrayList<JTextField>();
    private List<JTextField> authorsCity = new ArrayList<JTextField>();
    private List<JTextField> authorsState = new ArrayList<JTextField>();
    private List<JTextField> authorsZip = new ArrayList<JTextField>();
    private List<JTextArea> authorsBio = new ArrayList<JTextArea>();

    public String getIsbnTf() {
        return isbnTf.getText();
    }

    public String getTitleTf() {
        return titleTf.getText();
    }

    public String getCopiesTf() {
        return copiesTf.getText();
    }

    private JComboBox maxCheckoutDays;

    private AddBookWindow() {
        init();
    }

    public void init() {
        setTitle("Book Management");

        mainPanel = new JPanel(new BorderLayout());
        authorsContainerPanel = new JPanel(new GridLayout(0, 1));

        mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        scrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMinimumSize(new Dimension(600, 500));
        scrollPane.setPreferredSize(new Dimension(600, 500));

//        scrollPane.getViewport().setMinimumSize(new Dimension(600, 500));
//        scrollPane.getViewport().setPreferredSize(new Dimension(600, 500));

        getContentPane().add(BorderLayout.CENTER, scrollPane);

        setupVisuals();
    }

    private void setupVisuals() {
        JPanel topContainerPanel = new JPanel(new GridBagLayout());
        JPanel goBackPanel = new JPanel();
        JPanel leftSidePanel = new JPanel(new GridLayout(4, 2, 10, 0));

        JButton goBackBtn = new JButton("<< Go Back");
        goBackPanel.add(goBackBtn);
        goBackBtn.addActionListener(v -> {
            LibrarySystem.hideAllWindows();
            LibrarySystem.librarySystemInstance.initAndShow();
        });

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JPanel rightSidePanel = new JPanel();
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> saveNewAuthor());

        rightSidePanel.add(saveBtn);

        JLabel isbnLabel = new JLabel("Isbn");
        JLabel titleLabel = new JLabel("Title");
        JLabel maxCheckoutDayLabel = new JLabel("Max Checkout");
        JLabel copiesLabel = new JLabel("Num of Copies");

        isbnTf = new JTextField();
        titleTf = new JTextField();
        maxCheckoutDays = new JComboBox(checkoutBookLength);
        copiesTf = new JTextField();

        leftSidePanel.add(isbnLabel);
        leftSidePanel.add(titleLabel);
        leftSidePanel.add(isbnTf);
        leftSidePanel.add(titleTf);
        leftSidePanel.add(maxCheckoutDayLabel);
        leftSidePanel.add(copiesLabel);
        leftSidePanel.add(maxCheckoutDays);
        leftSidePanel.add(copiesTf);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        topContainerPanel.add(goBackPanel, gridBagConstraints);

//        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.9;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        topContainerPanel.add(leftSidePanel, gridBagConstraints);

        gridBagConstraints.weightx = 0.1;
        topContainerPanel.add(rightSidePanel, gridBagConstraints);

        JPanel moreAuthorsBtnPanel = new JPanel();
        moreAuthorsBtnPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        JButton addMoreAuthorBtn = new JButton("Add More Author");
        addMoreAuthorBtn.addActionListener(e -> {
            addAuthorsView();
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        moreAuthorsBtnPanel.add(addMoreAuthorBtn);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        topContainerPanel.add(moreAuthorsBtnPanel, gridBagConstraints);

        addAuthorsView();
        mainPanel.add(topContainerPanel, BorderLayout.NORTH);

        setSize(new Dimension(600, 500));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveNewAuthor() {

    }

    private void addAuthorsView() {
        JPanel totalContainer = new JPanel(new GridLayout(4, 3));
        totalContainer.setPreferredSize(new Dimension(550, 200));
        GridLayout gridLayout = new GridLayout(2, 3, 15, 0);
        JPanel topAuthorsPanel = new JPanel(gridLayout);
        JPanel secondAuthorsPanel = new JPanel(gridLayout);
        JPanel thirdAuthorsPanel = new JPanel(gridLayout);

        JLabel firstNameLabel = new JLabel("Firstname");
        JLabel lastNameLabel = new JLabel("Lastname");
        JLabel phoneLabel = new JLabel("Phone");
        JLabel stateLabel = new JLabel("State");
        JLabel cityLabel = new JLabel("City");
        JLabel streetLabel = new JLabel("Street");
        JLabel zipLabel = new JLabel("Zip");
        JLabel bioLabel = new JLabel("Bio");

        JTextField firstNameTf = new JTextField();
        JTextField lastNameTf = new JTextField();
        JTextField phoneTf = new JTextField();
        JTextField stateTf = new JTextField();
        JTextField cityTf = new JTextField();
        JTextField streetTf = new JTextField();
        JTextField zipTf = new JTextField();
        JTextArea bioTa = new JTextArea(3, 6);

        topAuthorsPanel.add(firstNameLabel);
        topAuthorsPanel.add(lastNameLabel);
        topAuthorsPanel.add(phoneLabel);
        topAuthorsPanel.add(firstNameTf);
        topAuthorsPanel.add(lastNameTf);
        topAuthorsPanel.add(phoneTf);

        secondAuthorsPanel.add(stateLabel);
        secondAuthorsPanel.add(cityLabel);
        secondAuthorsPanel.add(streetLabel);
        secondAuthorsPanel.add(stateTf);
        secondAuthorsPanel.add(cityTf);
        secondAuthorsPanel.add(streetTf);

        thirdAuthorsPanel.add(zipLabel);
        thirdAuthorsPanel.add(bioLabel);
        thirdAuthorsPanel.add(zipTf);
        thirdAuthorsPanel.add(bioTa);

        totalContainer.add(topAuthorsPanel);
        totalContainer.add(secondAuthorsPanel);
        totalContainer.add(thirdAuthorsPanel);

        authorsContainerPanel.add(totalContainer);
        mainPanel.add(authorsContainerPanel);
    }

    private void saveBook() {

    }

    public static void main(String[] args) {
        AddBookWindow.addBookWindowInstance.init();
    }
}
