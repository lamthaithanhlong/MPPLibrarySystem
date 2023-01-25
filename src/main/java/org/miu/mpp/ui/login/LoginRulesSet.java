package org.miu.mpp.ui.login;

import org.miu.mpp.ui.ruleset.RuleException;
import org.miu.mpp.ui.ruleset.RuleSet;
import org.miu.mpp.ui.ruleset.RuleSetFactory;

import java.awt.*;
import java.util.List;

public class LoginRulesSet implements RuleSet {
    String id, password;

    @Override
    public void applyRules(Component ob) throws RuleException {
        LoginWindow loginWindow = (LoginWindow) ob;

        id = loginWindow.getIdValue();
        password = loginWindow.getPasswordValue();

        if (RuleSetFactory.isAnyEmpty(List.of(id, password)))
            throw new RuleException("id or password cannot be empty");
    }
}
