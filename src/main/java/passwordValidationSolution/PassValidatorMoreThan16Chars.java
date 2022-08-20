package passwordValidationSolution;

public class PassValidatorMoreThan16Chars extends PasswordValidator {

    public PassValidatorMoreThan16Chars(String password) {
        super(password,16);
    }

    public boolean isValid() {
        return hasMatchWithAllValidationRules();
    }

    @Override
    protected boolean hasMatchWithAllValidationRules() {
        return super.hasMatchWithAllValidationRules() && rules.containsAtLeastOneUnderscore();
    }

}
