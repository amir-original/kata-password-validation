package PasswordValiationStrategy;

import java.util.LinkedHashSet;
import java.util.Set;

import static PasswordValiationStrategy.ErrorCode.*;

public class PassValidatorMoreThan16Chars implements PasswordValidatorStrategy {

    private PasswordValidationRules rules;
    private Set<ErrorCode> errorCodes = new LinkedHashSet<>();

    public PassValidatorMoreThan16Chars() {
        this.rules = new PasswordValidationRules(16);
    }



    @Override
    public boolean isValid(String password) {
        checkAllValidationRules(password);

        return errorCodes.isEmpty();
    }

    private void checkAllValidationRules(String password) {
        if (!rules.hasTheMinRequirementLength(password)) errorCodes.add(INVALID_PASSWORD_LENGTH);
        if (!rules.containsAtLeastOneLowercaseChar(password)) errorCodes.add(MISSING_LOWERCASE);
        if (!rules.containsAtLeastOneUppercaseChar(password)) errorCodes.add(MISSING_UPPERCASE);
        if(!rules.containsAtLeastOneUnderscore(password)) errorCodes.add(MISSING_UNDERSCORE);
    }
}
