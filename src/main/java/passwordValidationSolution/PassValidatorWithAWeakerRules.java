package passwordValidationSolution;

import static passwordValidationSolution.ErrorCode.*;

public class PassValidatorWithAWeakerRules extends PasswordValidator {

    private static final int NUMBER_OF_FAILURES_ALLOWED = 1;

    public PassValidatorWithAWeakerRules(String password) {
        super(password, 8);
    }

    @Override
    public boolean isValid() {
        return hasMatchWithDefaultValidationRules();
    }

    @Override
    protected boolean hasMatchWithDefaultValidationRules() {
        checkAllValidation();

        return hasMatchAllValidationRules();
    }

    private void checkAllValidation() {
        if (!rules.hasTheMinRequirementLength()) errorCodes.add(INVALID_PASSWORD_LENGTH);
        if (!rules.containsAtLeastOneUppercaseChar()) errorCodes.add(MISSING_UPPERCASE);
        if (!rules.containsAtLeastOneNumber()) errorCodes.add(MISSING_NUMBER);
        if (!rules.containsAtLeastOneUnderscore()) errorCodes.add(MISSING_UNDERSCORE);
    }

    @Override
    protected boolean hasMatchAllValidationRules() {
        if (hasMatchWithTheNumberOfAllowedFailure()) return true;

        errorCodes.add(ILLEGAL_NUMBER_OF_FAILURES_ALLOWED);
        throw new InvalidPasswordException(errorCodes);
    }

    private boolean hasMatchWithTheNumberOfAllowedFailure() {
        return errorCodes.size() <= NUMBER_OF_FAILURES_ALLOWED;
    }
}
