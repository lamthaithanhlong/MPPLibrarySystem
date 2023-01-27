package org.miu.mpp.ui;

import org.miu.mpp.models.Auth;
import org.miu.mpp.ui.admin.addmember.AddMemberWindow;
import org.miu.mpp.ui.admin.addbook.AddBookWindow;
import org.miu.mpp.ui.admin.addbookcopy.AddBookCopyWindow;
import org.miu.mpp.ui.base.JFrameAddMultiple;
import org.miu.mpp.ui.checkoutbook.CheckoutBookWindow;
import org.miu.mpp.ui.checkoutbook.CheckoutHistoryWindow;
import org.miu.mpp.ui.login.LoginWindow;
import org.miu.mpp.ui.overduepublication.OverduePublicationWindow;
import org.miu.mpp.ui.returnbook.ReturnBookWindow;
import org.miu.mpp.utils.ControllerInterface;
import org.miu.mpp.utils.SystemController;
import org.miu.mpp.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibrarySystem extends JFrameAddMultiple {
    public static LibrarySystem librarySystemInstance = new LibrarySystem();
    ControllerInterface ci = new SystemController();

    private JButton checkoutHistoryBtn;
    private JButton returnBookBtn;
    private JButton checkoutBooksBtn;
    private JButton overduePublicationsBtn;
    private JButton addMemberBtn;
    private JButton addNewBookBtn;
    private JButton addBookCopy;
    private JPanel panel;
    private ImageIcon backgroundImage;

    private static JFrameAddMultiple[] allWindows = {
            LibrarySystem.librarySystemInstance,
            LoginWindow.loginWindowInstance,
            AddBookWindow.addBookWindowInstance,
            CheckoutBookWindow.checkoutBookWindowInstance,
            CheckoutHistoryWindow.checkoutHistoryWindowInstance,
            AddBookCopyWindow.addBookCopyWindowInstance,
            ReturnBookWindow.returnBookWindowInstance
    };

    private LibrarySystem() {
    }

    public static void hideAllWindows() {
        for (JFrameAddMultiple frame : allWindows) {
            frame.setVisible(false);
        }
    }

    public void initAndShow() {
        setTitle("Welcome to Starboys LMS");
        setSize(800, 600);

        addDashboardItems();
    }

    public void addDashboardItems() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // path to the background image
        backgroundImage = new ImageIcon(System.getProperty("user.dir") + "/src/main/resources/dashboard-bk.png");

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.CENTER;

        checkoutHistoryBtn = new JButton("Checkout History");
        checkoutHistoryBtn.setPreferredSize(new Dimension(150, 40));
        panel.add(checkoutHistoryBtn, constraints);
        checkoutHistoryBtn.addActionListener(new CheckoutHistoryClickListener());

        returnBookBtn = new JButton("Return Book");
        constraints.gridx = 1;
        constraints.gridy = 0;
        returnBookBtn.setPreferredSize(new Dimension(150, 40));
        panel.add(returnBookBtn, constraints);
        returnBookBtn.addActionListener(new ReturnBookClickListener());

        checkoutBooksBtn = new JButton("Checkout Book");
        constraints.gridx = 2;
        constraints.gridy = 0;
        checkoutBooksBtn.setPreferredSize(new Dimension(150, 40));
        panel.add(checkoutBooksBtn, constraints);
        checkoutBooksBtn.addActionListener(new CheckoutBookClickListener());

        overduePublicationsBtn = new JButton("Overdue publications");
        constraints.gridx = 0;
        constraints.gridy = 1;
        overduePublicationsBtn.setPreferredSize(new Dimension(150, 40));
        panel.add(overduePublicationsBtn, constraints);
        overduePublicationsBtn.addActionListener(new OverduePublicationsClickListener());

        addMemberBtn = new JButton("Add member");
        constraints.gridx = 1;
        constraints.gridy = 1;
        addMemberBtn.setPreferredSize(new Dimension(150, 40));
        panel.add(addMemberBtn, constraints);
        addMemberBtn.addActionListener(new AddMemberClickListener());

        addNewBookBtn = new JButton("Add Book");
        constraints.gridx = 2;
        constraints.gridy = 1;
        addNewBookBtn.setPreferredSize(new Dimension(150, 40));
        panel.add(addNewBookBtn, constraints);
        addNewBookBtn.addActionListener(new BookClickListener());

        addBookCopy = new JButton("Add Book Copy");
        constraints.gridx = 3;
        constraints.gridy = 1;
        addBookCopy.setPreferredSize(new Dimension(150, 40));
        panel.add(addBookCopy, constraints);
        addBookCopy.addActionListener(new AddBookCopyListener());

        removeComponentBasedOnRole();

        add(panel);
        setVisible(true);
    }

    private void removeComponentBasedOnRole() {
        final Auth loggedInUserAuth = SystemController.loggedInUser.getAuthorization();

        if (loggedInUserAuth == Auth.LIBRARIAN) {
            panel.remove(addMemberBtn);
            panel.remove(addNewBookBtn);
            panel.remove(addBookCopy);
        }

        if (loggedInUserAuth == Auth.ADMIN) {
            panel.remove(checkoutHistoryBtn);
            panel.remove(returnBookBtn);
            panel.remove(checkoutBooksBtn);
            panel.remove(overduePublicationsBtn);
        }
    }

    class ReturnBookClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LibrarySystem.hideAllWindows();

            ReturnBookWindow.returnBookWindowInstance.init();
            Util.centerFrameOnDesktop(ReturnBookWindow.returnBookWindowInstance);
            ReturnBookWindow.returnBookWindowInstance.setVisible(true);
        }
    }

    class CheckoutBookClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LibrarySystem.hideAllWindows();

            CheckoutBookWindow.checkoutBookWindowInstance.initData();
            Util.centerFrameOnDesktop(CheckoutBookWindow.checkoutBookWindowInstance);

            CheckoutBookWindow.checkoutBookWindowInstance.setVisible(true);
        }
    }

    class OverduePublicationsClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            LibrarySystem.hideAllWindows();

            OverduePublicationWindow.overduePublicationWindowInstace.initData();
            Util.centerFrameOnDesktop(OverduePublicationWindow.overduePublicationWindowInstace);
            OverduePublicationWindow.overduePublicationWindowInstace.setVisible(true);
        }
    }

    class AddMemberClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LibrarySystem.hideAllWindows();

            AddMemberWindow.addMemberWindowInstance.initData();
            Util.centerFrameOnDesktop(AddMemberWindow.addMemberWindowInstance);
            AddMemberWindow.addMemberWindowInstance.setVisible(true);
        }
    }

    class BookClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LibrarySystem.hideAllWindows();

            AddBookWindow.addBookWindowInstance.init();
            Util.centerFrameOnDesktop(AddBookWindow.addBookWindowInstance);

            AddBookWindow.addBookWindowInstance.setVisible(true);
        }
    }

    class AddBookCopyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LibrarySystem.hideAllWindows();

            AddBookCopyWindow.addBookCopyWindowInstance.init();
            Util.centerFrameOnDesktop(AddBookCopyWindow.addBookCopyWindowInstance);

            AddBookCopyWindow.addBookCopyWindowInstance.setVisible(true);
        }
    }

    class CheckoutHistoryClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LibrarySystem.hideAllWindows();

            CheckoutHistoryWindow.checkoutHistoryWindowInstance.initData();
            Util.centerFrameOnDesktop(CheckoutHistoryWindow.checkoutHistoryWindowInstance);
            CheckoutHistoryWindow.checkoutHistoryWindowInstance.setVisible(true);
        }
    }

    public static void main(String[] args) {
        LibrarySystem.librarySystemInstance.initAndShow();
    }
}
