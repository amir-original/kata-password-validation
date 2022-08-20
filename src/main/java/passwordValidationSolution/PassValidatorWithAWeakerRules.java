package passwordValidationSolution;

import static passwordValidationSolution.ErrorCode.ILLEGAL_NUMBER_OF_FAILURES_ALLOWED;

public class PassValidatorWithAWeakerRules extends PasswordValidator {

    private static final int NUMBER_OF_FAILURES_ALLOWED = 1;

    public PassValidatorWithAWeakerRules(String password) {
        super(password, 8);
    }

    @Override
    public boolean isValid() {
        return hasMatchWithAllValidationRules();
    }

    @Override
    protected boolean hasMatchWithAllValidationRules() {
        int numOfAcceptedRules = 0;
        try {
            if (rules.hasTheMinRequirementLength()) numOfAcceptedRules++;
            if (rules.containsAtLeastOneUppercaseChar()) numOfAcceptedRules++;
            if (rules.containsAtLeastOneNumber()) numOfAcceptedRules++;
            if (rules.containsAtLeastOneUnderscore()) numOfAcceptedRules++;

        } catch (InvalidPasswordException ignored) {}

        return hasMatchWithTheNumberOfAllowedFailure(numOfAcceptedRules);
    }

    private boolean hasMatchWithTheNumberOfAllowedFailure(int numberOfAcceptedRules) {
        if (numberOfAcceptedRules <= NUMBER_OF_FAILURES_ALLOWED)
            throw new InvalidPasswordException(ILLEGAL_NUMBER_OF_FAILURES_ALLOWED);

        return true;
    }
}
