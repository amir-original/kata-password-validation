package passwordValidationSolution;

public class PassValidatorMoreThan6Chars extends PasswordValidator {

    public PassValidatorMoreThan6Chars(String password) {
        super(password,6);
    }

    public boolean isValid() {
        return hasMatchWithAllValidationRules();
    }

    @Override
    protected boolean hasMatchWithAllValidationRules() {
        return super.hasMatchWithAllValidationRules() && rules.containsAtLeastOneNumber();
    }

}
