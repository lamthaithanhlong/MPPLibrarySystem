package org.miu.mpp.ui.ruleset;

import org.miu.mpp.ui.admin.addbook.AddBookRuleSet;
import org.miu.mpp.ui.admin.addbook.AddBookWindow;
import org.miu.mpp.ui.admin.addbookcopy.AddBookCopyRuleSet;
import org.miu.mpp.ui.admin.addbookcopy.AddBookCopyWindow;
import org.miu.mpp.ui.admin.addmember.AddMemberRuleSet;
import org.miu.mpp.ui.admin.addmember.AddMemberWindow;
import org.miu.mpp.ui.checkoutbook.CheckoutBookRulesSet;
import org.miu.mpp.ui.checkoutbook.CheckoutBookWindow;
import org.miu.mpp.ui.login.LoginRulesSet;
import org.miu.mpp.ui.login.LoginWindow;
import org.miu.mpp.ui.returnbook.ReturnBookWindow;
import org.miu.mpp.ui.returnbook.ReturnBookWindowRuleSet;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final public class RuleSetFactory {
    private RuleSetFactory() {
    }

    static HashMap<Class<? extends Component>, RuleSet> map = new HashMap<>();

    static {
        map.put(LoginWindow.class, new LoginRulesSet());
        map.put(AddBookWindow.class, new AddBookRuleSet());
        map.put(CheckoutBookWindow.class, new CheckoutBookRulesSet());
        map.put(AddBookCopyWindow.class, new AddBookCopyRuleSet());
        map.put(ReturnBookWindow.class, new ReturnBookWindowRuleSet());
        map.put(AddMemberWindow.class, new AddMemberRuleSet());
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

    public static boolean isValidZipCode(String zipCode) {
        // Regular expression to match a US zip code
        String zipCodePattern = "^[0-9]{5}(?:-[0-9]{4})?$";

        // Compile the regular expression
        Pattern pattern = Pattern.compile(zipCodePattern);

        // Check if the zip code matches the regular expression
        Matcher matcher = pattern.matcher(zipCode);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Regular expression to match a phone number with an optional country code
        String phoneNumberPattern = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";

        // Compile the regular expression
        Pattern pattern = Pattern.compile(phoneNumberPattern);

        // Check if the phone number matches the regular expression
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
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

    public static boolean isValidIsbn(String isbn) {
        if (isbn.isEmpty())
            return false;
        for (int i = 0; i < isbn.length(); i++) {

            if (!Character.isDigit(isbn.charAt(i)) ||
                    (!Character.isDigit(isbn.charAt(i)) && !String.valueOf(isbn.charAt(i)).equals("-"))) {
                return false;
            }
        }
        return true;
    }
}
