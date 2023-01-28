package org.miu.mpp.ui.admin.addbookcopy;

import org.miu.mpp.models.Author;
import org.miu.mpp.models.Book;
import org.miu.mpp.ui.LibrarySystem;
import org.miu.mpp.ui.base.Dialog;
import org.miu.mpp.ui.base.SearchPanelTopPanel;
import org.miu.mpp.ui.base.UIHelper;
import org.miu.mpp.ui.ruleset.RuleException;
import org.miu.mpp.ui.ruleset.RuleSetFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.List;

public class AddBookCopyWindow extends SearchPanelTopPanel implements UIHelper {
    public static AddBookCopyWindow addBookCopyWindowInstance = new AddBookCopyWindow();

    private boolean isInitialized = false;

    private AddBookCopyController addBookCopyController = new AddBookCopyController();

    private DefaultTableModel model;
    private JTable table;

    private JPanel mainPanel;

    private JScrollPane scrollPane;

    private List<Book> books;

    private String userSearchText;

    public String getUserSearchText() {
        return userSearchText;
    }

    private AddBookCopyWindow() {
    }

    public void init() {
        setTitle("Book Management");
        setupSearchPanelDetails();
        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 110, 560, 408);
        add(scrollPane);

        setupVisuals();

        setLayout(null);
        setSize(new Dimension(600, 500));
        setVisible(true);

        isInitialized = true;
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    private void setupVisuals() {
        table = new JTable();
        table.setFocusable(false);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point point = me.getPoint();
                int row = table.rowAtPoint(point);
                if (me.getClickCount() == 2 && row != -1) {
                    composeDialogAndDisplayBookInfo(row);
                }
            }
        });
        model = new DefaultTableModel();
        String[] column = {"ISBN", "Title", "Authors", "Max. Checkout", "Copies"};
        model.setColumnIdentifiers(column);
        table.setModel(model);
        scrollPane.setViewportView(table);
        table.setEnabled(false);
        populateData();
    }

    private void composeDialogAndDisplayBookInfo(int rowIndex) {
        String s = (String) JOptionPane.showInputDialog(
                this,
                "How many copies would you like to add?",
                "Add Book Copy",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                null, null);

        if ((s != null) && (s.length() > 0)) {
            try {
                int numberEntered = Integer.parseInt(s);
                books.sort(Comparator.comparing(Book::getIsbn));
                Book book = books.get(rowIndex);

                for (int i = 0; i < numberEntered; i++) {
                    book.addCopy();
                }
                addBookCopyController.saveBook(book);

                new Dialog("Success", "Copies added Successfully", false);
                populateData();
            } catch (NumberFormatException e) {
                new Dialog("Error", "Invalid number entered", true);
            }
        }
    }

    private void setupSearchPanelDetails() {
        setSearchText("isbn");
        setButtonText("search");
        initPanel();
    }

    @Override
    public void clickListenerForSearchBtn(String searchText, ActionEvent e) {
        try {
            //set user search text here so that it can be accessed in the AddBookWindowRuleSet Class
            userSearchText = searchText;
            RuleSetFactory.getRuleSet(this).applyRules(this);

            final Book matchingBook = addBookCopyController.getBook(searchText);

            if (matchingBook != null) {
                model.setRowCount(0);
                //book found matching isbn, update list
                books.clear();
                books.add(matchingBook);

                for (Book book : books) {
                    String author = "";
                    for (Author a : book.getAuthors()) {
                        if (!author.equals("")) author += ", ";
                        author += a.getFirstName() + " " + a.getLastName();
                    }
                    model.insertRow(0, new Object[]{book.getIsbn(), book.getTitle(), author, book.getMaxCheckoutLength(), book.getNumCopies()});
                }
                model.fireTableDataChanged();
            } else {
                //no book found matching isbn, show error dialog
                new Dialog("Error", "Book with isbn - " + searchText + " not found", true);
            }

        } catch (RuleException ruleException) {
            new Dialog("Error", ruleException.getMessage(), true);
            System.out.println(ruleException.getMessage());
        }
    }

    @Override
    public void clickListenerForBackBtn(ActionEvent e) {
        LibrarySystem.hideAllWindows();
        LibrarySystem.librarySystemInstance.initAndShow();
    }

    public void populateData() {
        model.setRowCount(0);
        books = addBookCopyController.getAllBooks();
        books.sort((b1, b2) -> b2.getIsbn().compareTo(b1.getIsbn()));
        for (Book book : books) {
            String author = "";
            for (Author a : book.getAuthors()) {
                if (!author.equals("")) author += ", ";
                author += a.getFirstName() + " " + a.getLastName();
            }
            model.insertRow(0, new Object[]{book.getIsbn(), book.getTitle(), author, book.getMaxCheckoutLength(), book.getNumCopies()});
        }
        model.fireTableDataChanged();
    }

    public static void main(String[] args) {
        AddBookCopyWindow.addBookCopyWindowInstance.init();
    }
}
