package org.miu.mpp.ui.checkoutbook;

import org.miu.mpp.ui.login.LoginWindow;
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


        if (RuleSetFactory.isAnyEmpty(List.of(checkoutBookWindow.getIsbnLabelText(), checkoutBookWindow.getIsbnLabelText())))
            throw new RuleException("id or password cannot be empty");
    }
}
