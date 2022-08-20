package passwordValidationSolution;

public abstract class PasswordValidator {

    protected PasswordValidationRules rules;

    public PasswordValidator(String password, int minRequiredLength) {
        rules = new PasswordValidationRules(password, minRequiredLength);
    }

    public abstract boolean isValid();

    protected boolean hasMatchWithAllValidationRules() {
        return rules.hasTheMinRequirementLength() &&
                rules.containsAtLeastOneLowercaseChar() &&
                rules.containsAtLeastOneUppercaseChar();
    }

}
