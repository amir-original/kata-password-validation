package PasswordValiationStrategy;

import static PasswordValiationStrategy.ErrorCode.MISSING_NUMBER;
import static PasswordValiationStrategy.PasswordLength.MORE_THAN_6_CHARS;


public class PassValidatorMoreThan6Chars extends AbstractPasswordValidator {

    public PassValidatorMoreThan6Chars() {
        super(MORE_THAN_6_CHARS);
    }

    @Override
    protected void checkAllValidationRules(String password) {
       super.checkAllValidationRules(password);

        if(!rules.containsAtLeastOneNumber(password)) errorCodes.add(MISSING_NUMBER);
    }
}
