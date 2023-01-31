package org.miu.mpp.ui.admin.addbook;

import org.miu.mpp.models.Book;
import org.miu.mpp.ui.ruleset.RuleException;
import org.miu.mpp.ui.ruleset.RuleSet;
import org.miu.mpp.ui.ruleset.RuleSetFactory;

import java.awt.*;
import java.util.List;
import java.util.regex.Pattern;


public class AddBookRuleSet implements RuleSet {
    private String isbn, title, copies;
    private List<String> firstNames, lastNames, telePhones, bios, streets, cities, states, zips;

    private Book checkedBook;

    @Override
    public void applyRules(Component ob) throws RuleException {

        AddBookWindow addBookWindow = (AddBookWindow) ob;

        isbn = addBookWindow.getIsbnTf();
        title = addBookWindow.getTitleTf();
        copies = addBookWindow.getCopytf();
        firstNames = addBookWindow.getTxtFirstName();
        lastNames = addBookWindow.getTxtLastName();
        telePhones = addBookWindow.getTxtTelephone();
        bios = addBookWindow.getTxaBio();
        streets = addBookWindow.getTxtStreet();
        cities = addBookWindow.getTxtCity();
        states = addBookWindow.getTxtState();
        zips = addBookWindow.getTxtZip();
        checkedBook = addBookWindow.getBookToCheck();

        if (RuleSetFactory.isAnyEmpty(List.of(isbn, title, copies)))
            throw new RuleException("Please ensure no fields are empty");

        if (!RuleSetFactory.isNumeric(copies))
            throw new RuleException("Copies can only be numeric");

        if (!RuleSetFactory.isValidIsbn(isbn))
            throw new RuleException("ISBN can only contain numbers and \"-\"");

        if (checkedBook != null) {
            throw new RuleException("ISBN already exists");
        }

        for (int i = 0; i < firstNames.size(); i++) {
            if (firstNames.get(i).trim().equals("") || lastNames.get(i).trim().equals("") || telePhones.get(i).trim().equals("") ||
                    streets.get(i).trim().equals("") || cities.get(i).trim().equals("") || states.get(i).trim().equals("") ||
                    zips.get(i).trim().equals("") || bios.get(i).trim().equals("")) {
                throw new RuleException("Please fill all Authors fields");
            }

            if (!Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$").matcher(telePhones.get(i).trim()).matches()) {
                throw new RuleException("Please fill all Authors telephone fields with correct format");
            }

            if (!(zips.get(i).trim().length() == 5 || zips.get(i).trim().length() == 6)) {
                throw new RuleException("Please fill Authors zip fields with correct format");
            }
        }
    }
}
