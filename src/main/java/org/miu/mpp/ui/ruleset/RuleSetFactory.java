package org.miu.mpp.ui.ruleset;

import org.miu.mpp.ui.admin.addbook.AddBookRuleSet;
import org.miu.mpp.ui.admin.addbook.AddBookWindow;
import org.miu.mpp.ui.admin.addbookcopy.AddBookCopyRuleSet;
import org.miu.mpp.ui.admin.addbookcopy.AddBookCopyWindow;
import org.miu.mpp.ui.checkoutbook.CheckoutBookRulesSet;
import org.miu.mpp.ui.checkoutbook.CheckoutBookWindow;
import org.miu.mpp.ui.login.LoginRulesSet;
import org.miu.mpp.ui.login.LoginWindow;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

final public class RuleSetFactory {
    private RuleSetFactory() {
    }

    static HashMap<Class<? extends Component>, RuleSet> map = new HashMap<>();

    static {
        map.put(LoginWindow.class, new LoginRulesSet());
        map.put(AddBookWindow.class, new AddBookRuleSet());
        map.put(CheckoutBookWindow.class, new CheckoutBookRulesSet());
        map.put(AddBookCopyWindow.class, new AddBookCopyRuleSet());
    }

    public static RuleSet getRuleSet(Component c) {
        Class<? extends Component> cl = c.getClass();
        if (!map.containsKey(cl)) {
            throw new IllegalArgumentException("No RuleSet found for this Component");
        }
        return map.get(cl);
    }

    public static boolean isNumeric(String str) {
        if (str.isEmpty())
            return false;
        for (int i = 0; i < str.length(); i++) {

            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnyEmpty(List<String> values) {
        for (String value : values) {
            if (value.isBlank()) return true;
        }

        return false;
    }

    public static boolean isExactLength(String str, int len) {
        return str.length() == len;
    }

    public static boolean isbnFirstDigitOneOrZero(String isbn) {
        if (isNumeric(isbn) && isExactLength(isbn, 10)) {
            return String.valueOf(isbn.charAt(0)).equals("0") || String.valueOf(isbn.charAt(0)).equals("1");
        }

        return false;
    }

    public static boolean validateIsbnFirstThreeDigits(String isbn) {
        if (isNumeric(isbn) && isExactLength(isbn, 13)) {
            return String.valueOf(isbn.substring(0, 3)).equals("978") || String.valueOf(isbn.substring(0, 3)).equals("979");
        }

        return false;
    }
}
