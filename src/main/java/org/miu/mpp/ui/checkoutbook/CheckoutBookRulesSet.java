package org.miu.mpp.ui.checkoutbook;

import org.miu.mpp.ui.ruleset.RuleException;
import org.miu.mpp.ui.ruleset.RuleSet;
import org.miu.mpp.ui.ruleset.RuleSetFactory;

import java.awt.*;
import java.util.List;

/**
 * @author bazz
 * Date Jan 25 2023
 * Time 15:49
 */
public class CheckoutBookRulesSet implements RuleSet {

    @Override
    public void applyRules(Component ob) throws RuleException {
        CheckoutBookWindow checkoutBookWindow = (CheckoutBookWindow) ob;

        if (RuleSetFactory.isAnyEmpty(List.of(checkoutBookWindow.getMemberIdFieldText(), checkoutBookWindow.getIsbnFieldText()))) {

            System.out.println("Log checkout rule!!!");
            throw new RuleException("Member ID or ISBN cannot be empty");
        }

        if (!RuleSetFactory.isValidIsbn(checkoutBookWindow.getIsbnFieldText()))
            throw new RuleException("ISBN can only contain numbers and \"-\"");
    }
}
