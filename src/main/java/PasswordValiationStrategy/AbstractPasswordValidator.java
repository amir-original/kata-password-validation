package PasswordValiationStrategy;

import java.util.*;

import static PasswordValiationStrategy.ErrorCode.*;

public abstract class AbstractPasswordValidator implements PasswordValidatorStrategy {

    protected PasswordValidationRules rules;
    protected Set<ErrorCode> errorCodes = new LinkedHashSet<>();

    public AbstractPasswordValidator(PasswordLength passwordLength) {
        this.rules = new PasswordValidationRules(passwordLength);
    }

    @Override
    public boolean isValid(String password) {
        checkAllValidationRules(password);

        return hasMatchWithDefaultValidationRules(password);
    }

    protected boolean hasMatchWithDefaultValidationRules(String password) {
        if (errorCodes.isEmpty()) return true;

        throw new InvalidPasswordException(errorCodes);
    }

    protected void checkAllValidationRules(String password) {
        if (!rules.hasTheMinRequirementLength(password)) errorCodes.add(INVALID_PASSWORD_LENGTH);
        if (!rules.containsAtLeastOneLowercaseChar(password)) errorCodes.add(MISSING_LOWERCASE);
        if (!rules.containsAtLeastOneUppercaseChar(password)) errorCodes.add(MISSING_UPPERCASE);
    }
}
