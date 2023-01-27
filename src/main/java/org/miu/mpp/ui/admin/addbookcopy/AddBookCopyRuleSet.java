package org.miu.mpp.ui.admin.addbookcopy;

import org.miu.mpp.ui.ruleset.RuleException;
import org.miu.mpp.ui.ruleset.RuleSet;
import org.miu.mpp.ui.ruleset.RuleSetFactory;

import java.awt.*;
import java.util.List;

public class AddBookCopyRuleSet implements RuleSet {

    private String searchText;

    @Override
    public void applyRules(Component ob) throws RuleException {
        AddBookCopyWindow addBookCopyWindow = (AddBookCopyWindow) ob;

        searchText = addBookCopyWindow.getUserSearchText().trim();

        if (RuleSetFactory.isAnyEmpty(List.of(searchText)))
            throw new RuleException("Please enter an Isbn number before searching");
    }
}
