package PasswordValiationStrategy;

import static PasswordValiationStrategy.ErrorCode.*;
import static PasswordValiationStrategy.PasswordLength.MORE_THAN_8_CHARS;


public class PassValidatorMoreThan8Chars extends AbstractPasswordValidator {

    public PassValidatorMoreThan8Chars() {
        super(MORE_THAN_8_CHARS);
    }

    @Override
    protected void checkAllValidationRules(String password) {
        super.checkAllValidationRules(password);

        if (!rules.containsAtLeastOneNumber(password)) errorCodes.add(MISSING_NUMBER);
        if (!rules.containsAtLeastOneUnderscore(password)) errorCodes.add(MISSING_UNDERSCORE);
    }
}
