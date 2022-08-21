package PasswordValiationStrategy;

import java.util.LinkedHashSet;
import java.util.Set;

import static PasswordValiationStrategy.ErrorCode.*;
import static PasswordValiationStrategy.PasswordLength.MORE_THAN_6_CHARS;


public class PassValidatorMoreThan6Chars extends AbstractPasswordValidation {

    public PassValidatorMoreThan6Chars() {
        super(MORE_THAN_6_CHARS);
    }

    @Override
    public boolean isValid(String password) {
        checkAllValidationRules(password);

        return errorCodes.isEmpty();
    }

    @Override
    protected void checkAllValidationRules(String password) {
       super.checkAllValidationRules(password);

        if(!rules.containsAtLeastOneNumber(password)) errorCodes.add(MISSING_NUMBER);
    }
}
