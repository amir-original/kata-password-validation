package passwordValidationSolution;

public class PassValidatorWithAWeakerRules extends PasswordValidator {

    private static final int NUMBER_OF_FAILURES_ALLOWED = 1;

    public PassValidatorWithAWeakerRules(String password) {
        super(password, 8);
    }

    @Override
    public boolean isValid() {
        return matchesAllRequirements();
    }

    @Override
    protected boolean matchesAllRequirements() {
        int numberOfAcceptedRules = 0;
        try {
            if (rules.hasTheMinRequirementLength()) numberOfAcceptedRules++;
            if (rules.containsAtLeastOneUppercaseChar()) numberOfAcceptedRules++;
            if (rules.containsAtLeastOneNumber()) numberOfAcceptedRules++;
            if (rules.containsAtLeastOneUnderscore()) numberOfAcceptedRules++;

        } catch (InvalidPasswordException ignored) {}

        return matchRequirements(numberOfAcceptedRules);
    }

    private boolean matchRequirements(int numberOfAcceptedRules) {
        if (numberOfAcceptedRules <= NUMBER_OF_FAILURES_ALLOWED)
            throw new InvalidPasswordException(ErrorCode.ILLEGAL_NUMBER_OF_FAILURES_ALLOWED);

        return true;
    }
}
