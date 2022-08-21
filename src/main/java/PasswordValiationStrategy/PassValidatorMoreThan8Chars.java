package PasswordValiationStrategy;

import static PasswordValiationStrategy.ErrorCode.MISSING_NUMBER;
import static PasswordValiationStrategy.ErrorCode.MISSING_UNDERSCORE;


public class PassValidatorMoreThan8Chars extends AbstractPasswordValidation {
    public PassValidatorMoreThan8Chars() {
        this.rules = new PasswordValidationRules(6);
    }


    @Override
    public boolean isValid(String password) {
        checkAllValidationRules(password);

        return errorCodes.isEmpty();
    }

    protected void checkAllValidationRules(String password) {
        super.checkAllValidationRules(password);

        if (!rules.containsAtLeastOneNumber(password)) errorCodes.add(MISSING_NUMBER);
        if (!rules.containsAtLeastOneUnderscore(password)) errorCodes.add(MISSING_UNDERSCORE);
    }
}
