package org.miu.mpp.ui.login;

import org.miu.mpp.ui.ruleset.RuleException;
import org.miu.mpp.ui.ruleset.RuleSet;
import org.miu.mpp.ui.ruleset.RuleSetFactory;
import org.miu.mpp.utils.AppStringConstants;

import java.awt.*;
import java.util.List;

public class LoginRulesSet implements RuleSet {
    String userId, userPassword;

    @Override
    public void applyRules(Component ob) throws RuleException {
        LoginWindow loginWindow = (LoginWindow) ob;

        userId = loginWindow.getIdValue();
        userPassword = loginWindow.getPasswordValue();

        if (RuleSetFactory.isAnyEmpty(List.of(userId, userPassword))) {
            throw new RuleException(AppStringConstants.ID_PASSWORD_CANNOT_BE_EMPTY.getValue());
        }
    }
}
