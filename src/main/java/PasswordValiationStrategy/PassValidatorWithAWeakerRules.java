package PasswordValiationStrategy;

import java.util.LinkedHashSet;
import java.util.Set;

import static PasswordValiationStrategy.ErrorCode.*;


public class PassValidatorWithAWeakerRules implements PasswordValidatorStrategy {

    private static final int NUMBER_OF_FAILURES_ALLOWED = 1;

    private PasswordValidationRules rules;
    private Set<ErrorCode> errorCodes = new LinkedHashSet<>();

    public PassValidatorWithAWeakerRules() {
        this.rules = new PasswordValidationRules(8);
    }


    @Override
    public boolean isValid(String password) {
        return hasMatchWithDefaultValidationRules(password);
    }

    protected boolean hasMatchWithDefaultValidationRules(String password) {
        checkAllValidationRules(password);
        return hasMatchAllValidationRules();
    }


    protected void checkAllValidationRules(String password) {
        if (!rules.hasTheMinRequirementLength(password)) errorCodes.add(INVALID_PASSWORD_LENGTH);
        if (!rules.containsAtLeastOneUppercaseChar(password)) errorCodes.add(MISSING_UPPERCASE);
        if (!rules.containsAtLeastOneNumber(password)) errorCodes.add(MISSING_NUMBER);
        if (!rules.containsAtLeastOneUnderscore(password)) errorCodes.add(MISSING_UNDERSCORE);
    }


    protected boolean hasMatchAllValidationRules() {
        if (hasMatchWithTheNumberOfAllowedFailure()) return true;

        errorCodes.add(ILLEGAL_NUMBER_OF_FAILURES_ALLOWED);
        throw new InvalidPasswordException(errorCodes);
    }

    private boolean hasMatchWithTheNumberOfAllowedFailure() {
        return errorCodes.size() <= NUMBER_OF_FAILURES_ALLOWED;
    }
}
