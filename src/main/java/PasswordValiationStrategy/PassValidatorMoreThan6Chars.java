package PasswordValiationStrategy;

import java.util.LinkedHashSet;
import java.util.Set;

import static PasswordValiationStrategy.ErrorCode.*;


public class PassValidatorMoreThan6Chars extends AbstractPasswordValidation {

    public PassValidatorMoreThan6Chars() {
        this.rules = new PasswordValidationRules(6);
    }

    @Override
    public boolean isValid(String password) {
        checkAllValidationRules(password);

        return errorCodes.isEmpty();
    }

    protected void checkAllValidationRules(String password) {
       super.checkAllValidationRules(password);

        if(!rules.containsAtLeastOneNumber(password)) errorCodes.add(MISSING_NUMBER);
    }
}
