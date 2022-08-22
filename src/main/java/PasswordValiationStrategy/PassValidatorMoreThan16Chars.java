package PasswordValiationStrategy;

import static PasswordValiationStrategy.ErrorCode.MISSING_UNDERSCORE;
import static PasswordValiationStrategy.PasswordLength.MORE_THAN_16_CHARS;

public class PassValidatorMoreThan16Chars extends AbstractPasswordValidator {

    public PassValidatorMoreThan16Chars() {
        super(MORE_THAN_16_CHARS);
    }

    @Override
    protected void checkAllValidationRules(String password) {
        super.checkAllValidationRules(password);

        if(!rules.containsAtLeastOneUnderscore(password)) errorCodes.add(MISSING_UNDERSCORE);
    }
}
