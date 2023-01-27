package org.miu.mpp.ui.admin.addbook;

import org.miu.mpp.ui.ruleset.RuleException;
import org.miu.mpp.ui.ruleset.RuleSet;
import org.miu.mpp.ui.ruleset.RuleSetFactory;

import java.awt.*;
import java.util.List;

public class AddBookRuleSet implements RuleSet {
    private String isbn, title, copies;

    @Override
    public void applyRules(Component ob) throws RuleException {

        AddBookWindow addBookWindow = (AddBookWindow) ob;

        isbn = addBookWindow.getIsbnTf();
        title = addBookWindow.getTitleTf();
        copies = addBookWindow.getCopiesTf();

        if (RuleSetFactory.isAnyEmpty(List.of(isbn, title, copies)))
            throw new RuleException("Please ensure no fields are empty");

        if (!RuleSetFactory.isNumeric(copies))
            throw new RuleException("Copies can only be numeric");

        if (!RuleSetFactory.isNumeric(isbn))
            throw new RuleException("Isbn can only be Numeric");
    }
}
