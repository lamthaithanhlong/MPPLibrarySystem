package org.miu.mpp.ui.admin.addbook;

import org.miu.mpp.models.Address;
import org.miu.mpp.models.Author;
import org.miu.mpp.models.Book;
import org.miu.mpp.ui.LibrarySystem;
import org.miu.mpp.ui.admin.addbookcopy.AddBookCopyWindow;
import org.miu.mpp.ui.base.Dialog;
import org.miu.mpp.ui.base.JFrameAddMultiple;
import org.miu.mpp.ui.base.UIHelper;
import org.miu.mpp.ui.ruleset.RuleException;
import org.miu.mpp.ui.ruleset.RuleSetFactory;
import org.miu.mpp.utils.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddBookWindow extends JFrameAddMultiple implements UIHelper {
    private static final long serialVersionUID = 1L;
    public static final AddBookWindow addBookWindowInstance = new AddBookWindow();
    AddBookController bc = new AddBookController();
    private boolean isInitialized = false;
    private int numOfAuthor = 0;

    private JPanel panel;
    private JScrollPane scrollPane;
    private JTextField isbnTf, titleTf, copytf;

    public String getIsbnTf() {
        return isbnTf.getText();
    }

    public String getTitleTf() {
        return titleTf.getText();
    }

    public String getCopytf() {
        return copytf.getText();
    }

    private JComboBox<String> maxList;

    JPanel authorsPanel;

    private Book bookToCheck;

    private void setBookToCheck(Book book) {
        this.bookToCheck = book;
    }

    public Book getBookToCheck() {
        return bookToCheck;
    }

    private List<JTextField> txtFirstName = new ArrayList<>();
    private List<JTextField> txtLastName = new ArrayList<>();
    private List<JTextField> txtTelephone = new ArrayList<>();
    private List<JTextField> txtStreet = new ArrayList<>();
    private List<JTextField> txtCity = new ArrayList<>();
    private List<JTextField> txtState = new ArrayList<>();
    private List<JTextField> txtZip = new ArrayList<>();
    private List<JTextArea> txaBio = new ArrayList<>();

    public List<String> getTxtFirstName() {
        List<String> enteredFirstName = new ArrayList<>();
        for (JTextField textField : txtFirstName)
            enteredFirstName.add(textField.getText());
        return enteredFirstName;
    }

    public List<String> getTxtLastName() {
        List<String> enteredLastName = new ArrayList<>();
        for (JTextField textField : txtLastName)
            enteredLastName.add(textField.getText());
        return enteredLastName;
    }

    public List<String> getTxtTelephone() {
        List<String> enteredTelephone = new ArrayList<>();
        for (JTextField textField : txtTelephone)
            enteredTelephone.add(textField.getText());
        return enteredTelephone;
    }

    public List<String> getTxtStreet() {
        List<String> enteredStreet = new ArrayList<>();
        for (JTextField textField : txtStreet)
            enteredStreet.add(textField.getText());
        return enteredStreet;
    }

    public List<String> getTxtCity() {
        List<String> enteredCity = new ArrayList<>();
        for (JTextField textField : txtCity)
            enteredCity.add(textField.getText());
        return enteredCity;
    }

    public List<String> getTxtState() {
        List<String> enteredState = new ArrayList<>();
        for (JTextField textField : txtState)
            enteredState.add(textField.getText());
        return enteredState;
    }

    public List<String> getTxtZip() {
        List<String> enteredZip = new ArrayList<>();
        for (JTextField textField : txtZip)
            enteredZip.add(textField.getText());
        return enteredZip;
    }

    public List<String> getTxaBio() {
        List<String> enteredBio = new ArrayList<>();
        for (JTextArea textField : txaBio)
            enteredBio.add(textField.getText());
        return enteredBio;
    }

    private AddBookWindow() {
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddBookWindow.addBookWindowInstance.init();
                    AddBookWindow.addBookWindowInstance.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void cleanPanel() {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        txtFirstName.clear();
        txtLastName.clear();
        txtTelephone.clear();
        txtStreet.clear();
        txtCity.clear();
        txtState.clear();
        txtZip.clear();
        txaBio.clear();
        numOfAuthor = 0;
    }

    public void init() {
        // TODO Auto-generated method stub
        if (isInitialized) {
            cleanPanel();
        } else {
            authorsPanel = new JPanel(new GridLayout(0, 2, 20, 0));
            authorsPanel.setBorder(new EmptyBorder(5, 10, 5, 10));

            panel = new JPanel();
            panel.setBorder(new EmptyBorder(5, 10, 5, 10));
            panel.setPreferredSize(new Dimension(600, 500));

            scrollPane = new JScrollPane(authorsPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setPreferredSize(new Dimension(350, 0));

            add(panel);
            setSize(new Dimension(600, 500));
        }

        setTitle("Add New Book");

        panel.setLayout(new BorderLayout());
        JPanel backButtonPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel isbnLayout = new JPanel();
        JPanel titleLayout = new JPanel();
        JPanel maxCheckoutLayout = new JPanel();
        JPanel numOfCopiesLayout = new JPanel();
        topPanel.setPreferredSize(new Dimension(220, 100));
        JPanel addBtnsPanel = new JPanel();

        JButton btnback = new JButton("<< Go Back");
        btnback.addActionListener(e -> {
            LibrarySystem.hideAllWindows();
            Util.centerFrameOnDesktop(LibrarySystem.librarySystemInstance);
            LibrarySystem.librarySystemInstance.setVisible(true);
        });
        JButton btnAddMoreAuthor = new JButton("Add More Author");
        btnAddMoreAuthor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addAuthor();
                panel.revalidate();
                panel.repaint();
            }
        });

        JButton btnadd = new JButton("Add");
        btnadd.addActionListener(e -> {
            addNewBook();
        });
        addBtnsPanel.add(btnadd);
        addBtnsPanel.add(btnAddMoreAuthor);

        backButtonPanel.add(btnback, BorderLayout.WEST);
        backButtonPanel.add(addBtnsPanel, BorderLayout.EAST);
        panel.add(backButtonPanel, BorderLayout.NORTH);

        JLabel idLabel = new JLabel("ISBN");
        isbnLayout.add(idLabel);

        isbnTf = new JTextField();
        isbnTf.setColumns(10);
        isbnLayout.add(isbnTf);
        topPanel.add(isbnLayout);

        JLabel nameLabel = new JLabel("Title");
        titleLayout.add(nameLabel);

        titleTf = new JTextField();
        titleTf.setColumns(10);
        titleLayout.add(titleTf);
        topPanel.add(titleLayout);

        JLabel maxLabel = new JLabel("Max Checkout");
        maxCheckoutLayout.add(maxLabel);

        maxList = new JComboBox<String>(new String[]{"21 Days", "7 Days"});
        maxCheckoutLayout.add(maxList);
        topPanel.add(maxCheckoutLayout);

        JLabel copiesLabel = new JLabel("Num of Copies");
        numOfCopiesLayout.add(copiesLabel);

        copytf = new JTextField();
        copytf.setColumns(10);
        numOfCopiesLayout.add(copytf);
        topPanel.add(numOfCopiesLayout);

        panel.add(topPanel, BorderLayout.WEST);
        JPanel panelFour = new JPanel();
        addBtnsPanel.add(panelFour);
        addBtnsPanel.add(new JPanel());

        addBtnsPanel.add(btnAddMoreAuthor);

        addAuthor();

        pack();

        isInitialized = true;
    }

    private void addNewBook() {
        try {

            setBookToCheck(bc.getBook(getIsbnTf().trim()));
            RuleSetFactory.getRuleSet(this).applyRules(this);

            // add the entered inputs to the table
            List<Author> authors = new ArrayList<Author>();
            for (int i = 0; i < txtFirstName.size(); i++) {
                authors.add(new Author(txtFirstName.get(i).getText(), txtLastName.get(i).getText(), txtTelephone.get(i).getText(), new Address(txtStreet.get(i).getText(), txtCity.get(i).getText(), txtState.get(i).getText(), txtZip.get(i).getText()), txaBio.get(i).getText()));
            }
            Book book = new Book(isbnTf.getText(), titleTf.getText(), maxList.getSelectedItem().toString() == "7 Days" ? 7 : 21, authors);
            for (int i = 1; i < Integer.parseInt(copytf.getText()); i++) {
                book.addCopy();
            }
            bc.addBook(book);

            new Dialog("Success", "Book added successfully", false);
            LibrarySystem.hideAllWindows();

            if (!AddBookCopyWindow.addBookCopyWindowInstance.isInitialized())
                AddBookCopyWindow.addBookCopyWindowInstance.init();
            AddBookCopyWindow.addBookCopyWindowInstance.populateData();
            Util.centerFrameOnDesktop(AddBookCopyWindow.addBookCopyWindowInstance);
            AddBookCopyWindow.addBookCopyWindowInstance.setVisible(true);

        } catch (RuleException ruleException) {
            new Dialog("Error", ruleException.getMessage(), true);
        }
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    private void addAuthor() {
        JLabel lblAuthor = new JLabel("Author " + (numOfAuthor + 1));
        lblAuthor.setHorizontalAlignment(SwingConstants.CENTER);
        authorsPanel.add(lblAuthor);
        authorsPanel.add(new JPanel());

        JLabel lblFirstName = new JLabel("First Name");
        authorsPanel.add(lblFirstName);

        JTextField txtFirstName = new JTextField();
        txtFirstName.setColumns(10);
        authorsPanel.add(txtFirstName);
        this.txtFirstName.add(txtFirstName);

        JLabel lblLastName = new JLabel("Last Name");
        authorsPanel.add(lblLastName);
        JTextField txtLastName = new JTextField();
        txtLastName.setColumns(10);
        authorsPanel.add(txtLastName);
        this.txtLastName.add(txtLastName);

        JLabel lblTelephone = new JLabel("Telephone");
        authorsPanel.add(lblTelephone);
        JTextField txtTelephone = new JTextField();
        txtTelephone.setColumns(10);
        authorsPanel.add(txtTelephone);
        this.txtTelephone.add(txtTelephone);

        JLabel lblBio = new JLabel("Bio");
        authorsPanel.add(lblBio);
        JTextArea txaBio = new JTextArea();
        txaBio.setColumns(10);
        authorsPanel.add(txaBio);
        this.txaBio.add(txaBio);

        JLabel lblStreet = new JLabel("Street Address");
        authorsPanel.add(lblStreet);
        JTextField txtStreet = new JTextField();
        txtStreet.setColumns(10);
        authorsPanel.add(txtStreet);
        this.txtStreet.add(txtStreet);

        JLabel lblCity = new JLabel("City");
        authorsPanel.add(lblCity);
        JTextField txtCity = new JTextField();
        txtCity.setColumns(10);
        authorsPanel.add(txtCity);
        this.txtCity.add(txtCity);

        JLabel lblState = new JLabel("State");
        authorsPanel.add(lblState);
        JTextField txtState = new JTextField();
        txtState.setColumns(10);
        authorsPanel.add(txtState);
        this.txtState.add(txtState);

        JLabel lblZip = new JLabel("ZIP");
        authorsPanel.add(lblZip);
        JTextField txtZip = new JTextField();
        txtZip.setColumns(10);
        authorsPanel.add(txtZip);
        this.txtZip.add(txtZip);

        panel.add(scrollPane);

        numOfAuthor++;
    }
}
