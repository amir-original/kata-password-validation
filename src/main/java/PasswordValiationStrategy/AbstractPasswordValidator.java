package PasswordValiationStrategy;

import java.util.LinkedHashSet;
import java.util.Set;

import static PasswordValiationStrategy.ErrorCode.*;

public abstract class AbstractPasswordValidator implements PasswordValidatorStrategy {

    protected PasswordValidationRules rules;
    protected Set<ErrorCode> errorCodes = new LinkedHashSet<>();

    public AbstractPasswordValidator(PasswordLength passwordLength) {
        this.rules = new PasswordValidationRules(passwordLength);
    }

    protected void checkAllValidationRules(String password) {
        if (!rules.hasTheMinRequirementLength(password)) errorCodes.add(INVALID_PASSWORD_LENGTH);
        if (!rules.containsAtLeastOneLowercaseChar(password)) errorCodes.add(MISSING_LOWERCASE);
        if (!rules.containsAtLeastOneUppercaseChar(password)) errorCodes.add(MISSING_UPPERCASE);
    }
}
