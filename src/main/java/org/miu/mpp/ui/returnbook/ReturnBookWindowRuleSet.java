package org.miu.mpp.ui.returnbook;

import org.miu.mpp.ui.ruleset.RuleException;
import org.miu.mpp.ui.ruleset.RuleSet;
import org.miu.mpp.ui.ruleset.RuleSetFactory;

import java.awt.*;
import java.util.List;

public class ReturnBookWindowRuleSet implements RuleSet {
    private String searchText;

    @Override
    public void applyRules(Component ob) throws RuleException {
        ReturnBookWindow returnBookWindow = (ReturnBookWindow) ob;
        searchText = returnBookWindow.getUserSearchText();

        if (RuleSetFactory.isAnyEmpty(List.of(searchText)))
            throw new RuleException("Please enter an isbn number before searching");

//        if (!RuleSetFactory.isNumeric(searchText))
//            throw new RuleException("ISBN can only contain numbers");
    }
}
