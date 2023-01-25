package org.miu.mpp.ui.ruleset;

import java.awt.*;
import java.util.HashMap;

final public class RuleSetFactory {
    private RuleSetFactory() {
    }

    static HashMap<Class<? extends Component>, RuleSet> map = new HashMap<>();

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
