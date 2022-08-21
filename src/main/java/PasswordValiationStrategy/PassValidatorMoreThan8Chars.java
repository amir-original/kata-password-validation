package PasswordValiationStrategy;

import static PasswordValiationStrategy.ErrorCode.MISSING_NUMBER;
import static PasswordValiationStrategy.ErrorCode.MISSING_UNDERSCORE;
import static PasswordValiationStrategy.PasswordLength.MORE_THAN_8_CHARS;


public class PassValidatorMoreThan8Chars extends AbstractPasswordValidation {

    public PassValidatorMoreThan8Chars() {
        super(MORE_THAN_8_CHARS);
    }


    @Override
    public boolean isValid(String password) {
        checkAllValidationRules(password);

        return errorCodes.isEmpty();
    }

    @Override
    protected void checkAllValidationRules(String password) {
        super.checkAllValidationRules(password);

        if (!rules.containsAtLeastOneNumber(password)) errorCodes.add(MISSING_NUMBER);
        if (!rules.containsAtLeastOneUnderscore(password)) errorCodes.add(MISSING_UNDERSCORE);
    }
}
