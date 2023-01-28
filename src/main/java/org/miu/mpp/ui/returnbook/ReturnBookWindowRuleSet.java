package org.miu.mpp.ui.returnbook;

import org.miu.mpp.ui.ruleset.RuleException;
import org.miu.mpp.ui.ruleset.RuleSet;
import org.miu.mpp.ui.ruleset.RuleSetFactory;

import java.awt.*;
import java.util.List;

public class ReturnBookWindowRuleSet implements RuleSet {
    private String isbnSearchText;
    private String memberIdSearchText;

    @Override
    public void applyRules(Component ob) throws RuleException {
        ReturnBookWindow returnBookWindow = (ReturnBookWindow) ob;
        isbnSearchText = returnBookWindow.getIsbnTf();
        memberIdSearchText = returnBookWindow.getMemberTf();

        if (RuleSetFactory.isAnyEmpty(List.of(isbnSearchText, memberIdSearchText)))
            throw new RuleException("Both Member Id and Isbn must be entered");

//        if (!RuleSetFactory.isNumeric(searchText))
//            throw new RuleException("ISBN can only contain numbers");
    }
}
