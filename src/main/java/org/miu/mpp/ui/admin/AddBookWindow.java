package org.miu.mpp.ui.admin;

import org.miu.mpp.ui.base.JFrameAddMultiple;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddBookWindow extends JFrameAddMultiple {
    public static AddBookWindow AddBookWindowInstance = new AddBookWindow();

    String[] checkoutBookLength = {"7", "21"};

    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel isbnAndTitleLabelPanel;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        mainPanel = new JPanel(new BorderLayout());
        scrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(scrollPane);

        addUIElements();
    }

    private void addUIElements() {
        topPanel = new JPanel();
        topPanel.setSize(600, 300);
        topPanel.setBackground(Color.RED);
        isbnAndTitleLabelPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        JLabel isbnLabel = new JLabel("Isbn");
//        isbnLabel.setBounds(30, 30, 100, 30);

        isbnTf = new JTextField();
        isbnTf.setBounds(30, 60, 200, 35);

        JLabel titleLabel = new JLabel("Title");
//        titleLabel.setBounds(250, 30, 100, 30);

        isbnAndTitleLabelPanel.add(isbnLabel);
        isbnAndTitleLabelPanel.add(titleLabel);

        topPanel.add(isbnAndTitleLabelPanel);

        titleTf = new JTextField();
        titleTf.setBounds(250, 60, 200, 35);

        saveBtn = new JButton("Save");
        saveBtn.setBounds(470, 60, 100, 30);
        saveBtn.addActionListener(e -> {
            saveBook();
        });

        JLabel maxCheckoutDayLabel = new JLabel("Max Checkout");
        maxCheckoutDayLabel.setBounds(30, 100, 150, 30);

        maxCheckoutDays = new JComboBox(checkoutBookLength);
        maxCheckoutDays.setBounds(30, 130, 100, 30);

        JLabel copiesLabel = new JLabel("Num of Copies");
        copiesLabel.setBounds(250, 100, 100, 30);

        copiesTf = new JTextField();
        copiesTf.setBounds(250, 130, 50, 35);

        JSeparator separator = new JSeparator();
        separator.setBounds(20, 180, 550, 10);

        JButton addMoreAuthorBtn = new JButton("Add More Author");
        addMoreAuthorBtn.setBounds(420, 190, 150, 30);
        addMoreAuthorBtn.addActionListener(e -> {
            addMoreAuthors();
        });

//        addAll(List.of(saveBtn, isbnLabel, isbnTf, titleLabel, titleTf, maxCheckoutDayLabel, maxCheckoutDays, copiesLabel, copiesTf, separator, addMoreAuthorBtn));
        addAuthorView();

        mainPanel.add(topPanel, BorderLayout.NORTH);
        setVisible(true);
        setBounds(100, 100, 600, 500);
        setLocationRelativeTo(null);
    }

    private void addMoreAuthors() {

    }

    private void addAuthorView() {
        JLabel authorsLabel = new JLabel("Author " + (numOfAuthor + 1));

        mainPanel.add(authorsLabel);
    }

    private void saveBook() {

    }

    public static void main(String[] args) {
        AddBookWindow.AddBookWindowInstance.init();
    }
}
