package PasswordValiationStrategy;

import static PasswordValiationStrategy.ErrorCode.*;
import static PasswordValiationStrategy.PasswordLength.MORE_THAN_8_CHARS;


public class PassValidatorWithAWeakerRules extends AbstractPasswordValidation {

    private static final int NUMBER_OF_FAILURES_ALLOWED = 1;

    public PassValidatorWithAWeakerRules() {
        super(MORE_THAN_8_CHARS);
    }


    @Override
    public boolean isValid(String password) {
        return hasMatchWithDefaultValidationRules(password);
    }

    protected boolean hasMatchWithDefaultValidationRules(String password) {
        checkAllValidationRules(password);

        return hasMatchAllValidationRules();
    }

    @Override
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
