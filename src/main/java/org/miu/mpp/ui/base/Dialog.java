package org.miu.mpp.ui.base;

import javax.swing.*;

public class Dialog {
    private final String dialogTitle;
    private final String dialogMessage;
    private final boolean isErrorDialog;

    public Dialog(String dialogTitle, String dialogMessage, boolean isErrorDialog) {
        this.dialogTitle = dialogTitle;
        this.dialogMessage = dialogMessage;
        this.isErrorDialog = isErrorDialog;

        setDialogDetails();
    }

    private void setDialogDetails() {
        if (isErrorDialog) {
            JOptionPane.showMessageDialog(new JFrame(),
                    dialogMessage,
                    dialogTitle,
                    JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(new JFrame(),
                    dialogMessage,
                    dialogTitle,
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Dialog("Failure", "Awww!!! Too Bad", false);
    }
}
