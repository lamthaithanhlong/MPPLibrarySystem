package org.miu.mpp.utils;

import org.miu.mpp.models.CheckoutRecord;
import org.miu.mpp.ui.returnbook.CheckOutHistoryDto;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static void centerFrameOnDesktop(Component f) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int height = toolkit.getScreenSize().height;
        int width = toolkit.getScreenSize().width;
        int frameHeight = f.getSize().height;
        int frameWidth = f.getSize().width;
        f.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 3);
    }

    public static List<CheckOutHistoryDto> getCheckoutPojo(java.util.List<CheckoutRecord> allCheckoutRecords) {
        List<CheckOutHistoryDto> checkOutHistoryPojos = new ArrayList<>();

        allCheckoutRecords.forEach(v -> v.getEntries().forEach(y -> checkOutHistoryPojos
                .add(new CheckOutHistoryDto(y.getBookCopy(), y.getCheckoutDate(), y.getDueDate(), v.getMemberId(), y.getDueFee()))));

        return checkOutHistoryPojos;
    }
}
